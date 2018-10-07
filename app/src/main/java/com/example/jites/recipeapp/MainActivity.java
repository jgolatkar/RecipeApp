package com.example.jites.recipeapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edit,caloryBox;
    Button mButton;
    CheckBox check1, check2, check3,check4;
    ArrayList<String> objects = new ArrayList<>();

    final String app_id = "b1c20ee0";
    final String app_key = "5222b593e391b606d16ef8bfbc449c27";
    private RequestQueue mQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = findViewById(R.id.editText);
        mButton = findViewById(R.id.search);
        check1 = findViewById(R.id.checkBox1);
        check2 = findViewById(R.id.checkBox2);
        check3 = findViewById(R.id.checkBox3);
        check4 = findViewById(R.id.checkBox4);

        caloryBox = findViewById(R.id.calories);



        mQueue = Volley.newRequestQueue(this);
        mButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.v("EditText", edit.getText().toString());
                        String api_url = "https://api.edamam.com/search?" + "app_id=" + app_id + "&app_key=" + app_key + "&q=" + edit.getText().toString() +
                                "&calories"+caloryBox.getText().toString();
                        jsonParse(api_url);
                        Intent intent = new Intent(MainActivity.this,DisplayRecipesActivity.class);
                        //Log.v(Log.v());
                        //
                        //Bundle args = new Bundle();
                        //args.putSerializable("ARRAYLIST",objects);
                        intent.putStringArrayListExtra("recipeList",objects);
                        startActivity(intent);

                    }
                });


    }

    private void jsonParse(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                //ArrayList<JSONObject> objects = new ArrayList<>();
                try {
                    JSONArray hits = response.getJSONArray("hits");
                    //Log.v("Length", Integer.toString(hits.length()));
                        for (int i=0;i<hits.length();i++){
                            JSONObject recipe = hits.getJSONObject(i);
                            int totalTime = recipe.getJSONObject("recipe").getInt("totalTime");
                            int calories = recipe.getJSONObject("recipe").getInt("calories") / 10;
                            String[] caloryRange = caloryBox.getText().toString().split("-");
                            if(calories >= Integer.parseInt(caloryRange[0]) && calories <= Integer.parseInt(caloryRange[1])){
                                if(check1.isChecked()){
                                    if(totalTime > 0 && totalTime <= 30){
                                        Log.v("Recipe: ",recipe.getJSONObject("recipe").getString("label"));
                                        Log.v("Total Time: ", Integer.toString(recipe.getJSONObject("recipe").getInt("totalTime")));
                                        Log.v("Total Calories: ", Integer.toString(calories));
                                        objects.add(recipe.getJSONObject("recipe").getString("label"));
                                    }

                                }
                                if(check2.isChecked()){
                                    if(totalTime > 30 && totalTime <= 90){
                                        Log.v("Recipe: ",recipe.getJSONObject("recipe").getString("label"));
                                        Log.v("Total Time: ", Integer.toString(recipe.getJSONObject("recipe").getInt("totalTime")));
                                        Log.v("Total Calories: ", Integer.toString(calories));
                                        objects.add(recipe.getJSONObject("recipe").getString("label"));
                                    }

                                }
                                if(check3.isChecked()){
                                    if(totalTime > 90 && totalTime <= 180){
                                        Log.v("Recipe: ",recipe.getJSONObject("recipe").getString("label"));
                                        Log.v("Total Time: ", Integer.toString(recipe.getJSONObject("recipe").getInt("totalTime")));
                                        Log.v("Total Calories: ", Integer.toString(calories));
                                        objects.add(recipe.getJSONObject("recipe").getString("label"));
                                    }

                                }
                                if(check4.isChecked()){
                                    if(totalTime > 180){
                                        Log.v("Recipe: ",recipe.getJSONObject("recipe").getString("label"));
                                        Log.v("Total Time: ", Integer.toString(recipe.getJSONObject("recipe").getInt("totalTime")));
                                        Log.v("Total Calories: ", Integer.toString(calories));
                                        objects.add(recipe.getJSONObject("recipe").getString("label"));
                                    }
                            }


                            }


                            //Log.v("ArrayList: ", String.valueOf(objects));
                            //Log.v("Total time: ", recipe.getJSONObject("recipe").getString("totalTime"));


                    }
                    Log.v("ArrayList: ", String.valueOf(objects));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        });
        mQueue.add(jsonObjectRequest);
        //return hits;
    }

}
