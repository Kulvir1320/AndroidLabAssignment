package com.example.labassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button Add;
   ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Add = findViewById(R.id.btn_add);
        listView = findViewById(R.id.listview);






        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddLocationActivity.class);
                startActivity(intent);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,FavLocationActivity.class);
                    intent.putExtra("id",position);
                    startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String[] fvtLocations = new String[FavModel.FavLoc.size()];

        System.out.println(FavModel.FavLoc.size() + "-- size of array");



        for (int i = 0 ; i < FavModel.FavLoc.size() ; i ++){



            fvtLocations[i] = FavModel.FavLoc.get(i).getAddress() + (i+1);



        }









        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,fvtLocations);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


}
