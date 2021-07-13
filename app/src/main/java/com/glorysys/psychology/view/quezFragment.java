package com.glorysys.psychology.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.glorysys.psychology.MainActivity;
import com.glorysys.psychology.R;
import com.glorysys.psychology.model.DataBaseHelper;
import com.glorysys.psychology.model.JsonReader;
import com.glorysys.psychology.model.QuezModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class quezFragment extends Fragment implements JsonReader.IGetjsonResult {
    private static final String TAG = "quezFragment";
    List<QuezModel> quezModelList;
    TextView textViewTitle, textViewId;
    Button buttonNext;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6;
    RadioGroup radioGroup;
    int i = 0,s=0;
    List<QuezModel> listPoint=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quez, container, false);
        JsonReader jsonReader = new JsonReader(getContext());
        jsonReader.onReadJson(this);
        //////////
        textViewTitle = view.findViewById(R.id.textview_FragmentQuez_question);
        textViewId = view.findViewById(R.id.textview_FragmentQuez_id);
        buttonNext = view.findViewById(R.id.button_QuezFragment_next);
        buttonNext.setBackground(getContext().getResources().getDrawable(R.drawable.shape_background_button));
        radioButton1 = view.findViewById(R.id.radioButton1);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);
        radioButton4 = view.findViewById(R.id.radioButton4);
        radioButton5 = view.findViewById(R.id.radioButton5);
        radioButton6 = view.findViewById(R.id.radioButton6);
        radioGroup = view.findViewById(R.id.radioGroup_QuezFragment_radioGroup);

        ///////
        setArgument(i);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton1.isChecked() | radioButton2.isChecked() | radioButton3.isChecked() | radioButton4.isChecked() |
                        radioButton5.isChecked() | radioButton6.isChecked()) {

                    while (s<quezModelList.size()){
                        QuezModel quezModel=new QuezModel();
                        if(radioButton1.isChecked()) {
                            quezModel.setPoint(1);
                            listPoint.add(quezModel);
                            i++;
                            setArgument(i);
                            //radioGroup.clearCheck();

                        }
                        else if (radioButton2.isChecked()){
                            quezModel.setPoint(2);
                            listPoint.add(quezModel);
                            i++;
                            setArgument(i);
                            radioGroup.clearCheck();
                        }

                        else if (radioButton3.isChecked()){
                            quezModel.setPoint(3);
                            listPoint.add(quezModel);
                            i++;
                            setArgument(i);
                            radioGroup.clearCheck();
                        }

                        else if (radioButton4.isChecked()){
                            quezModel.setPoint(4);
                            listPoint.add(quezModel);
                            i++;
                            setArgument(i);
                            radioGroup.clearCheck();
                        }

                        else if (radioButton5.isChecked()){
                            quezModel.setPoint(5);
                            listPoint.add(quezModel);
                            i++;
                            setArgument(i);
                            radioGroup.clearCheck();
                        }

                        else if (radioButton6.isChecked()){
                            quezModel.setPoint(6);
                            listPoint.add(quezModel);
                            i++;
                            setArgument(i);
                            radioGroup.clearCheck();
                        }
                        break;
                    }


                }else {
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.notChoiseError), Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;
    }


    @Override
    public void onJsonResult(List<QuezModel> quezModels) {
        this.quezModelList = quezModels;
        Log.i(TAG, "onJsonResult: " + quezModels.get(5).getqTitle());
    }

    private void setArgument(int i) {
        while (i < quezModelList.size()) {
            textViewId.setText(quezModelList.get(i).getId() + (" از ") + quezModelList.size());
            textViewTitle.setText(quezModelList.get(i).getqTitle());
            s++;
            break;
        }
        if (i == (quezModelList.size() - 1)) {
            buttonNext.setText(getContext().getResources().getString(R.string.end));

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "end", Toast.LENGTH_SHORT).show();
                    if (radioButton1.isChecked() | radioButton2.isChecked() | radioButton3.isChecked() | radioButton4.isChecked() |
                            radioButton5.isChecked() | radioButton6.isChecked()) {
                        QuezModel quezModel=new QuezModel();
                        if(radioButton1.isChecked()) {
                            quezModel.setPoint(1);
                            listPoint.add(quezModel);

                        }
                        else if (radioButton2.isChecked()){
                            quezModel.setPoint(2);
                            listPoint.add(quezModel);

                        }

                        else if (radioButton3.isChecked()){
                            quezModel.setPoint(3);
                            listPoint.add(quezModel);

                        }

                        else if (radioButton4.isChecked()){
                            quezModel.setPoint(4);
                            listPoint.add(quezModel);

                        }

                        else if (radioButton5.isChecked()){
                            quezModel.setPoint(5);
                            listPoint.add(quezModel);

                        }

                        else if (radioButton6.isChecked()){
                            quezModel.setPoint(6);
                            listPoint.add(quezModel);

                        }
                        int k=0;
                        List<QuezModel> quezModelListfinal=new ArrayList<>();
                        for (int j = 0; j < 90; j++) {
                            while (k<listPoint.size()){
                                Log.i(TAG, "onClick:" +quezModelList.get(j).getId() +" ; point :  " +
                                        listPoint.get(k).getPoint()+" ; title : \n "+quezModelList.get(j).getqTitle());
                                QuezModel quezModel1=new QuezModel();
                                quezModel1.setId(quezModelList.get(j).getId());
                                quezModel1.setqTitle(quezModelList.get(j).getqTitle());
                                quezModel1.setPoint(listPoint.get(k).getPoint());
                                quezModelListfinal.add(quezModel1);
                                break;
                            }
                            k++;
                        }
                        DataBaseHelper dataBaseHelper=new DataBaseHelper(getContext());
                        int result=0;
                        for (int j = 0; j < 18; j++) {
                            for (int l = j; l <90 ; l+=18) {
                               result+=quezModelListfinal.get(l).getPoint();

                            }
                            dataBaseHelper.WriteOnDataBase(((j+1)+""),result);
                            Log.i(TAG, "result: "+"id: "+(j+1)+ " point :" +result);
                            result=0;
                        }



                        FragmentManager fragmentManager=getFragmentManager();
                        FragmentTransaction transaction=fragmentManager.beginTransaction();
                        QuezResultFragment quezResultFragment=new QuezResultFragment();
                        //quezResultFragment.setArguments(bundle);
                        transaction.replace(R.id.constraintL_Main_Fragment,quezResultFragment,"fragmentResult").commit();


                    }else {
                        Toast.makeText(getContext(), getContext().getResources().getString(R.string.notChoiseError), Toast.LENGTH_SHORT).show();

                    }

                    ////



                }
            });
        }

    }
    
}