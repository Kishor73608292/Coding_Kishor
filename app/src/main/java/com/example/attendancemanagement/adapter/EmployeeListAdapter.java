package com.example.attendancemanagement.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.attendancemanagement.R;
import com.example.attendancemanagement.api.Employee;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeListAdapter  extends RecyclerView.Adapter<EmployeeListAdapter.MyViewHolder> {

    Activity thisActivty;

    ArrayList<Employee> empArrayList = new ArrayList<Employee>();

    @NonNull
    @Override
    public EmployeeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list_inflate, parent, false);

        return new EmployeeListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
 final Employee e = empArrayList.get(position);

 holder.lblEmpID.setText(e.getEmployeeID());

 holder.lblEmpName.setText(e.getEmployeeName());

 holder.lblEmpEmail.setText(e.getEmployeeEmail());

 holder.lblEmpDesignation.setText(e.getDesignation());

 holder.lblEmpContactNamber.setText(e.getContactNumber());

    }

    @Override
    public int getItemCount() {
        return empArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblEmpID;
        public TextView lblEmpName;
        public TextView lblEmpEmail;
        public TextView lblEmpDesignation;
        public TextView lblEmpContactNamber;
        public SelectableRoundedImageView RoundImg;
        public LinearLayout lnrRight;
        public ImageView imgRight;




        public MyViewHolder(View view) {
            super(view);
            //final Typeface font_tyep2 = Typeface.createFromAsset(thisActivty.getAssets(), "fonts/American Typewriter Regular.ttf");
            lblEmpID = (TextView) view.findViewById(R.id.lblEmpID);
            lblEmpName = (TextView) view.findViewById(R.id.lblEmpName);
            lblEmpEmail = (TextView) view.findViewById(R.id.lblEmpEmail);
            lblEmpDesignation = (TextView) view.findViewById(R.id.lblEmpDesignation);
            lblEmpContactNamber = (TextView) view.findViewById(R.id.lblEmpContactNamber);
            imgRight = (ImageView) view.findViewById(R.id.imgRight);
            //lnrRight = (LinearLayout) view.findViewById(R.id.lnrRight);
            RoundImg = (SelectableRoundedImageView) view.findViewById(R.id.RoundImg);

            //lblFileName.setTypeface(font_tyep2);
        }

    }


    public EmployeeListAdapter(ArrayList<Employee> empArrayList, Activity thisActivty) {
        this.empArrayList = empArrayList;
        this.thisActivty = thisActivty;
    }
}
