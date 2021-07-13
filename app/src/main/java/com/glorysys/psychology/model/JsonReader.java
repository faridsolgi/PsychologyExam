package com.glorysys.psychology.model;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    private Context context;

    public JsonReader(Context context) {
        this.context = context;
    }

    private static final String TAG = "JsonReader";
    public void onReadJson(IGetjsonResult iGetjsonResult){

        try {
            JSONArray jsonArray=new JSONArray(loadJSONFromAsset(context));
            List<QuezModel> quezModelList=new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                QuezModel quezModel=new QuezModel();
                quezModel.setId(i+1);
                quezModel.setqTitle(jsonArray.getString(i));
                quezModelList.add(quezModel);
                Log.i(TAG, "onCreateView: "+ quezModelList.get(i).getId()+" : "+quezModelList.get(i).getqTitle());
            }
            iGetjsonResult.onJsonResult(quezModelList);

        }catch (Exception e){}




    }
    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public interface IGetjsonResult{
        void onJsonResult(List<QuezModel> quezModels);
    }


}
