package com.glorysys.psychology.view;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.glorysys.psychology.R;
import com.glorysys.psychology.model.DataBaseHelper;
import com.glorysys.psychology.model.QuezModel;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.List;


public class QuezResultFragment extends Fragment{
    private static final String TAG = "QuezResultFragment";
    Button buttonSendData;
    EditText editTextName,editTextAge,editTextSex,editTextEducation;
    RadioButton radioButton_Man,radioButton_Woman;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quez_result, container, false);
        buttonSendData=view.findViewById(R.id.button_result_buttonSendEmail);
        editTextName=view.findViewById(R.id.editText_Result_name);
        editTextAge=view.findViewById(R.id.editText_Result_age);
        radioButton_Man =view.findViewById(R.id.radioButton_result_sexMan);
        radioButton_Woman=view.findViewById(R.id.radioButton_result_sexWoman);
        editTextEducation=view.findViewById(R.id.editText_Result_education);
        buttonSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextName.getText())){
                    Toast.makeText(getContext(), "لطفا نام  کامل خود را وارد کنید", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(editTextAge.getText())){
                    Toast.makeText(getContext(), "لطفا سن خود را وارد کنید", Toast.LENGTH_SHORT).show();
                }else if(!radioButton_Man.isChecked() & !radioButton_Woman.isChecked()){
                    Toast.makeText(getContext(), "لطفا جنسیت خود را وارد کنید", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(editTextEducation.getText())){
                    Toast.makeText(getContext(), "لطفا تحصیلات خود را وارد نمایید", Toast.LENGTH_SHORT).show();
                }else{
                    String EmailAddress[]={"payrunfaraji@gmail.com"};
                    String Subject=" اپلیکیشن پرسشنامه "+ editTextName.getText().toString();
                    DataBaseHelper dataBaseHelper=new DataBaseHelper(getContext());
                    Cursor cursor =dataBaseHelper.ReadOnDataBase();
                    if (radioButton_Woman.isChecked()){
                        String Mail="نتیجه پرسشنامه "+editTextName.getText().toString() + ", " +editTextAge.getText().toString()
                                +"  ساله "+ ", جنسیت   " + radioButton_Woman.getText().toString() +" با مدرک تحصیلی  "+editTextEducation.getText().toString()
                                +" : \n ";

                        while (cursor.moveToNext()){
                            Mail+=cursor.getString(0)+" : "+cursor.getInt(1)+"\n";
                        }
                        Log.i(TAG, "data : \n"+Mail);
                        try {
                            Log.i(TAG, "Woman : \n ");
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_EMAIL,EmailAddress);
                            intent.putExtra(Intent.EXTRA_SUBJECT,Subject);
                            intent.putExtra(Intent.EXTRA_TEXT,Mail);
                            intent.setType("message/rfc822");
                            startActivity(Intent.createChooser(intent,"لطفا برنامه جیمیل خود را انتخاب کنید"));

                        }catch (Exception e){
                            e.getStackTrace();
                        }
                    }else{

                        String Mail="نتیجه پرسشنامه "+editTextName.getText().toString() + ", " +editTextAge.getText().toString()
                                +"  ساله "+ ", جنسیت   " + radioButton_Man.getText().toString() +" با مدرک تحصیلی "+editTextEducation.getText().toString()
                                +" : \n ";

                        while (cursor.moveToNext()){
                            Mail+=cursor.getString(0)+" : "+cursor.getInt(1)+"\n";
                        }
                        Log.i(TAG, "data : \n"+Mail);
                        try {
                            Log.i(TAG, "man : \n ");
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_EMAIL,EmailAddress);
                            intent.putExtra(Intent.EXTRA_SUBJECT,Subject);
                            intent.putExtra(Intent.EXTRA_TEXT,Mail);
                            intent.setType("message/rfc822");
                            startActivity(Intent.createChooser(intent,"لطفا برنامه جیمیل خود را انتخاب کنید"));

                        }catch (Exception e){
                            e.getStackTrace();
                        }


                    }

                }

            }
        });

        return view;
    }


}