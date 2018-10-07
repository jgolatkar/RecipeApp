package com.example.jites.recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayRecipesActivity extends AppCompatActivity {
    TextView txtView1,txtView2,txtView3;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipes);
        Intent intent = getIntent();
        ArrayList<String> jsonRecipes = intent.getStringArrayListExtra("recipeList");
        //Bundle args = intent.getBundleExtra("BUNDLE");
        //ArrayList<Object> jsonRecipes = (ArrayList<Object>) args.getSerializable("ARRAYLIST");

        Log.v("Intent list: ", String.valueOf(jsonRecipes));
        //txtView1 = findViewById(R.id.txtVw1);
        //txtView2 = findViewById(R.id.txtVw2);
        //txtView3 = findViewById(R.id.txtVw3);
        if(jsonRecipes.size() > 0) {

             //Log.v("Recipe", jsonRecipes.get(0).getJSONObject("recipe").getString("label"));
                //txtView1.setText(jsonRecipes.get(0));
                //txtView2.setText(jsonRecipes.get(0).getJSONObject("recipe").getString("calories"));
                //txtView3.setText(jsonRecipes.get(0).getJSONObject("recipe").getString("totalTime"));
            lv=(ListView)findViewById(R.id.lvList);
            lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , jsonRecipes));


        }
    }
}
