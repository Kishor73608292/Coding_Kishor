package com.example.attendancemanagement.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.attendancemanagement.AttendanceList;
import com.example.attendancemanagement.EmployeeList;
import com.example.attendancemanagement.LoginActivity;
import com.example.attendancemanagement.R;
import com.joooonho.SelectableRoundedImageView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends MyApplicationFragment {

    View v;
    //ProgressDialog loading;
    Activity thisActivity;

    private ScrollView scrMain;

    Resources res;

    ImageView backImage;
    TextView lblBack,lblHome;

    TextView lblEmployee,lblAttendance,lblAboutUs;

    RelativeLayout rytback;

    SelectableRoundedImageView imgLogo;

    LinearLayout lnrLogo,lnrEmp,lnrAtt,lnrAbout;

    ImageView imgFilter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = getActivity();
        res = thisActivity.getResources();
        v = inflater.inflate(R.layout.layout_main, null, false);
        init();
        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        thisActivity.finish();

    }

    @Override
    public void onResume() {
        lblHome.setVisibility(View.GONE);
        rytback.setVisibility(View.GONE);
        backImage.setVisibility(View.GONE);
        lblBack.setVisibility(View.GONE);
        imgFilter.setVisibility(View.GONE);
        super.onResume();
    }

    private void init() {

        scrMain = (ScrollView) v.findViewById(R.id.scrMain);

        lblHome = (TextView) thisActivity.findViewById(R.id.lblHome);

        lblHome.setText("HOME");

        imgFilter = (ImageView) thisActivity.findViewById(R.id.imgFilter);

        imgFilter.setVisibility(View.GONE);

        backImage = (ImageView) thisActivity.findViewById(R.id.backImage);

        lblBack = (TextView) thisActivity.findViewById(R.id.lblBack);

        rytback = (RelativeLayout) thisActivity .findViewById(R.id.rytback);

        lblEmployee = (TextView) v.findViewById(R.id.lblEmployee);

                lblAttendance = (TextView) v.findViewById(R.id.lblAttendance);

                lblAboutUs = (TextView) v.findViewById(R.id.lblAboutUs);

        imgLogo = (SelectableRoundedImageView) v.findViewById(R.id.imgLogo);
        imgLogo.setRotation(-45.0f);
                lblEmployee.setRotation(-45.0f);
        lblAboutUs.setRotation(-45.0f);
        lblAttendance.setRotation(-45.0f);


        lnrLogo = (LinearLayout) v.findViewById(R.id.lnrLogo);

        lnrEmp = (LinearLayout) v.findViewById(R.id.lnrEmp);

        lnrAtt = (LinearLayout) v.findViewById(R.id.lnrAtt);

        lnrAbout = (LinearLayout) v.findViewById(R.id.lnrAbout);

        backImage.setOnClickListener(new OnClick());
        lblBack.setOnClickListener(new OnClick());
        rytback.setOnClickListener(new OnClick());
        lnrLogo.setOnClickListener(new OnClick());
        lnrEmp.setOnClickListener(new OnClick());
        lnrAtt.setOnClickListener(new OnClick());
        lnrAbout.setOnClickListener(new OnClick());
        lblAttendance.setOnClickListener(new OnClick());
        lblEmployee.setOnClickListener(new OnClick());
        lblAboutUs.setOnClickListener(new OnClick());

    }

    public class OnClick implements  View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.backImage:
                case R.id.lblBack:
                case R.id.rytback:

                    break;

                case R.id.lnrEmp:
                case R.id.lblEmployee:
                    /*Intent intentemp = new Intent(thisActivity, EmployeeList.class);
                    startActivity(intentemp);
                    */

                    FragmentManager manager1 = getFragmentManager();
                    FragmentTransaction transaction1 = manager1.beginTransaction();
                    transaction1.setCustomAnimations(R.anim.slide_in_left,0,R.anim.slide_in_right, 0);
                    //transaction1.setCustomAnimations(0,0,0,R.anim.slide_out_left);
                    EmployeeListFragment frag1 = new EmployeeListFragment();
                    transaction1.add(R.id.frmRoot, frag1, "EmployeeListFragment");
                    transaction1.addToBackStack(null);
                    transaction1.commit();



                    break;

                case R.id.lnrAtt:
                case R.id.lblAttendance:
                    /*Intent intentatt = new Intent(thisActivity, AttendanceList.class);
                    startActivity(intentatt);
*/

                    FragmentManager manager2 = getFragmentManager();
                    FragmentTransaction transaction2 = manager2.beginTransaction();
                    transaction2.setCustomAnimations(R.anim.slide_in_left,0,R.anim.slide_in_right, 0);
                    //transaction1.setCustomAnimations(0,0,0,R.anim.slide_out_left);
                    AttendanceListFragment frag2 = new AttendanceListFragment();
                    transaction2.add(R.id.frmRoot, frag2, "AttendanceListFragment");
                    transaction2.addToBackStack(null);
                    transaction2.commit();


                    break;
                case R.id.lnrAbout:
                case R.id.lblAboutUs:
                   /* Intent intent = new Intent(thisActivity, LoginActivity.class);
                    startActivity(intent);*/

                    FragmentManager manager3 = getFragmentManager();
                    FragmentTransaction transaction3 = manager3.beginTransaction();
                    transaction3.setCustomAnimations(R.anim.slide_in_left,0,R.anim.slide_in_right, 0);
                    //transaction1.setCustomAnimations(0,0,0,R.anim.slide_out_left);
                    EntryFragment frag3 = new EntryFragment();
                    transaction3.add(R.id.frmRoot, frag3, "EntryFragment");
                    transaction3.addToBackStack(null);
                    transaction3.commit();


                    break;
            }
        }
    }

}
