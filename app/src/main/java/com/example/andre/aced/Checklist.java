package com.example.andre.aced;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Util.MyDividerItemDecoration;
import Util.Recylcer_Touch_Listener;
import Util.bottomNavBarHelper;
import data.DatabaseHelper;
import view.ChecklistAdapter;


//import view.ChecklistAdapter;

public class Checklist extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final int ACTIVITY_NUM  = 1;

    private ImageView moreOptions;
    public static DatabaseHelper db;
    private RecyclerView recyclerView1;
    public static ChecklistAdapter checklistAdapter;
    public List<model.Checklist>checklistList = new ArrayList<>();
    private static TextView noTaskView;
    private Button add;
    private CheckBox completeTask;
    private Context context = this;
    private NotificationManagerCompat notificationManagerCompat;
    private CountDownTimer countDownTimer;

    public static int user_picked_minute;
    public static int user_picked_hour;
    public static String titleTask;
    public static int hour;
    public static int minute;
    public int task_position;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);


        notificationManagerCompat =  NotificationManagerCompat.from(this);

        setUpBottomNavbar();

        //Field Corresponding XML Components
        moreOptions = (ImageView)findViewById(R.id.moreoption);
        moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Checklist.this, Option.class);
                Checklist.this.startActivity(intent1);
            }
        });

        add = (Button)findViewById(R.id.addTask);
        recyclerView1 = (RecyclerView)findViewById(R.id.recycler_view_checklist);
        noTaskView = (TextView)findViewById(R.id.empty_checklist_view);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTaskDialog(false, null, -1);
            }
        });

        db = new DatabaseHelper(this);
        checklistList.addAll(db.getAllTasks());


        checklistAdapter = new ChecklistAdapter(checklistList, this);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView1.setAdapter(checklistAdapter);
        checklistAdapter.notifyDataSetChanged();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                int position = viewHolder.getAdapterPosition();
                deleteTask(position);
                Toast.makeText(Checklist.this, "Task Completed!", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView1);


        recyclerView1.addOnItemTouchListener(new Recylcer_Touch_Listener(this,
                recyclerView1, new Recylcer_Touch_Listener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                showActionsDialog(position);
                }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        toggleEmptyTask();


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Toast.makeText(Checklist.this, "Reminder set!", Toast.LENGTH_LONG).show();

        user_picked_hour = hourOfDay;
        user_picked_minute = minute;

        checklistList.get(task_position).setHour(hourOfDay);
        checklistList.get(task_position).setMin(minute);

        db.insertHour(checklistList.get(task_position));
        db.insertMinute(checklistList.get(task_position));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        //Adds a Day for Midnight
        if(c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        startAlarm(c, titleTask);

    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startAlarm(Calendar c, String s){
        AlarmManager alarmManager = (AlarmManager)getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

    }


    private void completeTasks(final int position){

        completeTask = (CheckBox)findViewById(R.id.dot_checklist) ;

        completeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(completeTask.isChecked()){
                    completeTask.setActivated(true);
                    completeTask.setChecked(true);
                    Toast.makeText(Checklist.this, "Task Completed!", Toast.LENGTH_LONG).show();
                    db.deleteTask(checklistList.get(position));
                    checklistList.remove(position);
                    checklistAdapter.notifyItemRemoved(position);
                }
                else{
                    deleteTask(position);
                }
            }
        });

        toggleEmptyTask();
    }
    public String getUserTask(model.Checklist task){
        String result = task.getTask();
        return result;
    }
    private void showActionsDialog(final int position) {
        final more_info_task mit = new more_info_task();
        CharSequence colors[] = new CharSequence[]{"Edit", "Mark As Complete", "Set Reminder", "More Info"};


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showTaskDialog(true, checklistList.get(position), position);
                }
                 if (which == 1){
                        deleteTask(position);
                    }
                if(which == 2){
                    task_position = position;
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(), "time picker");
                    titleTask  = getUserTask(checklistList.get(position));

                }
                if(which == 3){
                    titleTask = getUserTask(checklistList.get(position));
                    hour = db.getHour(checklistList.get(position).getId());
                    minute = db.getMin(checklistList.get(position).getId());
                    Intent intent2 = new Intent(Checklist.this, more_info_task.class);
                    intent2.putExtra("taskname", titleTask);
                    intent2.putExtra("hour", hour);
                    intent2.putExtra("minute", minute);

                    Checklist.this.startActivity(intent2);
                    mit.position = position;


                }

            }
        });
        builder.show();
    }

    private void createTask(String task) {
        // inserting task in db and getting
        // newly inserted task id
        long id = db.insertChecklist(task);

        // get the newly inserted task from db
        model.Checklist n = db.getTask(id);

        if (n != null) {
            // adding new task to array list at 0 position
            checklistList.add(0, n);

            // refreshing the list
            checklistAdapter.notifyDataSetChanged();

            toggleEmptyTask();
        }

        checklistAdapter.notifyDataSetChanged();
    }

    private void updateTask(String task, int position) {
        model.Checklist n = checklistList.get(position);
        // updating task text
        n.setTask(task);

        // updating task in db
        db.updateTask(n);

        // refreshing the list
        checklistList.set(position, n);
        checklistAdapter.notifyItemChanged(position);

        toggleEmptyTask();
    }


    public void deleteTask(final int position) {
        // deleting the task from db

        db.deleteTask(checklistList.get(position));

        // removing the task from the list
        checklistList.remove(position);
        checklistAdapter.notifyItemRemoved(position);


        toggleEmptyTask();
    }

    private void showTaskDialog(final boolean shouldUpdate, final model.Checklist task, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.checklist_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Checklist.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputTask= view.findViewById(R.id.task);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_task_title) : getString(R.string.lbl_edit_task_title));

        if (shouldUpdate && task != null) {
            inputTask.setText(task.getTask());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputTask.getText().toString())) {
                    Toast.makeText(Checklist.this, "Enter task!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating task
                if (shouldUpdate && task != null) {
                    // update task by it's id
                    updateTask(inputTask.getText().toString(), position);
                } else {
                    // create new task
                    createTask(inputTask.getText().toString());
                }
            }
        });
    }


    private void toggleEmptyTask() {
        // you can check notesList.size() > 0

        if (db.getTaskCount() > 0) {
            noTaskView.setVisibility(View.GONE);
        } else {
            noTaskView.setVisibility(View.VISIBLE);
        }
    }


    private void setUpBottomNavbar(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomnavbar);
        bottomNavBarHelper.enbaleNav(Checklist.this, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


}


