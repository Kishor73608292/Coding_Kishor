package com.example.attendancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.attendancemanagement.fragments.MainFragment;

public class EntryActivity extends AppCompatActivity {

    Resources res;
    Activity thisActivity;
    ImageView backImage;
    TextView lblBack,lblHome;
    ImageView lblMenu,imgFilter;
    private FrameLayout frmRoot;

    RelativeLayout rytback,rytMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        thisActivity = EntryActivity.this;
        res = thisActivity.getResources();

        frmRoot = (FrameLayout) findViewById(R.id.frmRoot);
        rytMain = (RelativeLayout) findViewById(R.id.rytMain);

        imgFilter = (ImageView) findViewById(R.id.imgFilter);

//        final Typeface font_tyep2 = Typeface.createFromAsset(getResources().getAssets(), "fonts/regularsans.ttf");

        backImage = (ImageView) findViewById(R.id.backImage);

        lblBack = (TextView) findViewById(R.id.lblBack);

        //lblBack.setTypefac//e(font_tyep2);

        lblHome = (TextView) findViewById(R.id.lblHome);

        //lblHome.setTypeface(font_tyep2);

        lblMenu = (ImageView) findViewById(R.id.lblMenu);


        rytback = (RelativeLayout) findViewById(R.id.rytback);

        FragmentManager manager1 = getSupportFragmentManager();
        FragmentTransaction transaction1 = manager1.beginTransaction();
        transaction1.setCustomAnimations(R.anim.slide_in_left, 0);
        //transaction1.setCustomAnimations(0,0,0,R.anim.slide_out_left);
        MainFragment frag1 = new MainFragment();
        transaction1.add(R.id.frmRoot, frag1, "MainFragment");
        transaction1.addToBackStack("MainFragment");
        transaction1.commit();


    }
}