package view;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.andre.aced.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.Checklist;


public class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.MyViewHolder1> {

    //Constants and Fields
    private List<Checklist> taskList;
    private Context context;

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        public TextView task;
        public CheckBox checkBox;
        public TextView timestamp;

        public MyViewHolder1(View view) {
            super(view);
            task = view.findViewById(R.id.task_row);
            checkBox = view.findViewById(R.id.dot_checklist);

            timestamp = view.findViewById(R.id.timestamp_checklist);


        }
    }

    //Constructor
    public ChecklistAdapter(List <Checklist> taskList, Context context ){
        this.context = context;
        this.taskList = taskList;
    }


    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checklist_list_row, parent, false);



        return new MyViewHolder1(itemView);
    }
    //Reloads old rows with new data functionality of onBindViewHolder
    @Override
    public void onBindViewHolder(final MyViewHolder1 holder, int position) {
        final Checklist task = taskList.get(position);

        holder.task.setText(task.getTask());

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(task.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }




}
