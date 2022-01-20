package com.example.attendancemanagement.adapter;

import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.attendancemanagement.R;
import com.example.attendancemanagement.api.Attendance;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import rm.com.clocks.Clock;
import rm.com.clocks.ClockDrawable;
import rm.com.clocks.Stroke;

public class AttendanceListAdapter  extends RecyclerView.Adapter<AttendanceListAdapter.MyViewHolder> {

    Activity thisActivty;

    ArrayList<Attendance> AtList = new ArrayList<Attendance>();

    List<Entry> entries = new ArrayList<Entry>();

    ArrayList<String> labels = new ArrayList();

    public static final int[] COLORFUL_COLORS = {
            Color.rgb(62, 138, 85), Color.rgb(38, 48, 126), Color.rgb(137, 67, 76)

    };

    ArrayList<Integer> colors = new ArrayList<Integer>();



    //List<Entry> entries = new ArrayList<>();


    @NonNull
    @Override
    public AttendanceListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_list_inflate, parent, false);

        return new AttendanceListAdapter.MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Attendance at = AtList.get(position);


        if(labels.size()>0)
            labels.clear();

        labels.add("Available Leave");
        labels.add("Leave Taken");
        labels.add("Balance Leave");



        colors.clear();
        colors.add(thisActivty.getResources().getColor(R.color.Yellow));
        colors.add(thisActivty.getResources().getColor(R.color.light_pink));
        colors.add(thisActivty.getResources().getColor(R.color.Cyan));



        System.out.println("======>"+AtList.get(position).getDate());
        holder.lblEmpID.setText(at.getEmployeeID());

        holder.lblDate.setText(at.getDate());

        System.out.println("======>"+AtList.get(position).getLeave_Taken());
        System.out.println("======>"+AtList.get(position).getBalance_Leave());
        if(entries.size()>0){
            entries.clear();
        }

            entries.add(new Entry(12.0f,0));

            entries.add(new Entry(Float.parseFloat(at.getLeave_Taken()),1));
            entries.add(new Entry(Float.parseFloat(at.getBalance_Leave()),2));


        PieDataSet dataset = new PieDataSet(entries, "");
        //dataset.setSliceSpace(3);
        //dataset.setSelectionShift(5);
        dataset.setColors(colors);


        PieData data = new PieData(labels, dataset);
        holder.pieChart.setData(data);
        holder.pieChart.setDescription("");
        //holder.pieChart.animateXY(2000, 2000);
        holder.pieChart.invalidate();

        //holder.customAnalogClock.setAutoUpdate(true);
holder.lblITime.setText(at.getIn_Time());

holder.lblOTime.setText(at.getOut_Time());



       // holder.piclkerIn_Time.set


        //holder.piclkerIn_Time.time;
//
       if(at.getEmployeeID().equalsIgnoreCase("BT0001")) {
            holder.lblEmpName.setText("Kogila V");
        }

        else if(at.getEmployeeID().equalsIgnoreCase("BT0002")) {
           holder.lblEmpName.setText("Maruthupandian M");
       }

        else if(at.getEmployeeID().equalsIgnoreCase("BT0003")) {
           holder.lblEmpName.setText("Gajalakshmi R");
       }
        else if(at.getEmployeeID().equalsIgnoreCase("BT0004")) {
           holder.lblEmpName.setText("Amuda");
       }
        else if(at.getEmployeeID().equalsIgnoreCase("BT0005")) {
           holder.lblEmpName.setText("Senthilkumar P");
       }
        else if(at.getEmployeeID().equalsIgnoreCase("BT0006")) {
           holder.lblEmpName.setText("Slochana mary A");
       }
        else if(at.getEmployeeID().equalsIgnoreCase("BT0007")) {
           holder.lblEmpName.setText("Premkumar S");
       }
        else if(at.getEmployeeID().equalsIgnoreCase("BT0008")) {
           holder.lblEmpName.setText("Priyadharshini S");
       }
        else if(at.getEmployeeID().equalsIgnoreCase("BT0009")) {
           holder.lblEmpName.setText("Suganya A");
       }

        else if(at.getEmployeeID().equalsIgnoreCase("BT0010")) {
           holder.lblEmpName.setText("Kishore Kumar G");
       }
        else {
           holder.lblEmpName.setText("Umamaheswaran");
       }
//

//
//        holder.lblEmpDesignation.setText(e.getDesignation());
//
//        holder.lblEmpContactNamber.setText(e.getContactNumber());

    }

    @Override
    public int getItemCount() {
        return AtList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblEmpID;
        public TextView lblEmpName;
        public TextView lblDate;
        public PieChart pieChart;
        public TextView lblInTime;
        public TextView lblOutTime;
        public TextView lblITime;
        public TextView lblOTime;
        //private CustomAnalogClock customAnalogClock;






        public MyViewHolder(View view) {
            super(view);
            //final Typeface font_tyep2 = Typeface.createFromAsset(thisActivty.getAssets(), "fonts/American Typewriter Regular.ttf");
           lblEmpID = (TextView) view.findViewById(R.id.lblEmpID);
            lblEmpName = (TextView) view.findViewById(R.id.lblEmpName);
            lblDate = (TextView) view.findViewById(R.id.lblDate);
            pieChart = (PieChart) view.findViewById(R.id.leaveChart);
            lblInTime = (TextView) view.findViewById(R.id.lblInTime);
            lblOutTime = (TextView) view.findViewById(R.id.lblOutTime);
            lblITime = (TextView) view.findViewById(R.id.lblITime);
            lblOTime = (TextView) view.findViewById(R.id.lblOTime);

            //customAnalogClock = (CustomAnalogClock) view.findViewById(R.id.analog_clock);



        }

    }

    public AttendanceListAdapter(ArrayList<Attendance> AtList, Activity thisActivty) {
        this.AtList = AtList;
        System.out.println("======>"+AtList.get(AtList.size()-1).getEmployeeID());
        this.thisActivty = thisActivty;
    }
}
