package com.glorysys.psychology;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.glorysys.psychology.view.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStart=findViewById(R.id.button_main_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                quezFragment quezFragment=new quezFragment();
                transaction.add(R.id.constraintL_Main_Fragment,quezFragment,"fragmentQuiz").addToBackStack("fragmentQuiz");
                transaction.commit();
            }
        });

    }

    @Override
    public void onBackPressed() {
            DialogInterface.OnClickListener listenerDialog=new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            finish();
                            break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                    }
                }};
            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("آیا از پرسشنامه خارج میشوید؟").setPositiveButton("بله",listenerDialog).setNegativeButton("خیر",listenerDialog).show();

}}