package com.example.labassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button Add;
    SwipeMenuListView listView;
    ArrayAdapter<String> adapter;
    String[] fvtLocations;
    ArrayList<FavModel> arrayList;
    DatabaseHelper mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Add = findViewById(R.id.btn_add);
        listView = findViewById(R.id.listview);


        mDatabase = new DatabaseHelper(this);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
                startActivity(intent);

            }
        });


        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // update the list
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                openItem.setWidth(170);
                openItem.setTitle("update");
                openItem.setTitleSize(20);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
                //delete the list
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());

                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(200);
                deleteItem.setTitle("delete");
                deleteItem.setTitleSize(20);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:

                        Intent intent = new Intent(MainActivity.this, FavLocationActivity.class);
                        intent.putExtra("id", position);
                        startActivity(intent);
                        break;
                    case 1:
                        System.out.println("delete");

//                        FavModel.FavLoc.remove(position);
//                        if(mDatabase.deleteLocation(arrayList.get(position).getId()))
                         boolean b = mDatabase.deleteLocation(FavModel.FavLoc.get(position).getId());

                         if(b) {
                             loadLoactions();
                             System.out.println("edit");
                         } else
                             System.out.println("not ");


                        fvtLocations = new String[FavModel.FavLoc.size()];

                        System.out.println(FavModel.FavLoc.size() + "-- size of array");


                        for (int i = 0; i < FavModel.FavLoc.size(); i++) {


//                            fvtLocations[i] = FavModel.FavLoc.get(i).getAddress() + (i + 1);
                            if (FavModel.FavLoc.get(i).getAddress() != null) {
                                System.out.println("if part executed");
                                fvtLocations[i] = FavModel.FavLoc.get(i).getAddress() + (i + 1)  +  "\n" + FavModel.FavLoc.get(i).getVisited();

                            } else {
                                fvtLocations[i] = FavModel.FavLoc.get(i).getDate() +  "\n" + FavModel.FavLoc.get(i).getVisited();


                            }



                        }

                        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,fvtLocations);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;

                }
                return false;

            }

        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(MainActivity.this, FavLocationActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLoactions();
//        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadLoactions();
//        LocationAdapter locationAdapter = new LocationAdapter(this,R.layout.list_layout, FavModel.FavLoc);
//        listView.setAdapter(locationAdapter);

        fvtLocations = new String[FavModel.FavLoc.size()];
//         arrayList = new ArrayList<>(FavModel.FavLoc.size());
        System.out.println(FavModel.FavLoc.size() + "-- size of array");


        for (int i = 0; i < FavModel.FavLoc.size(); i++) {


            if (FavModel.FavLoc.get(i).getAddress() != null) {
                System.out.println("if part executed");

                System.out.println(FavModel.FavLoc.get(i).getAddress());
                System.out.println(FavModel.FavLoc.get(i).getLatitude());
                System.out.println(FavModel.FavLoc.get(i).getLongitude());
                System.out.println(FavModel.FavLoc.get(i).getVisited());

                fvtLocations[i] = FavModel.FavLoc.get(i).getAddress() + (i + 1) +   "\n" + FavModel.FavLoc.get(i).getVisited();

            } else {
                fvtLocations[i] = FavModel.FavLoc.get(i).getDate() + "\n" + FavModel.FavLoc.get(i).getVisited();


            }


            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, fvtLocations);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        }


    }

    private void loadLoactions() {
        Cursor cursor = mDatabase.getAllLocation();
        FavModel.FavLoc.clear();
        if(cursor.moveToFirst()) {
            do{
               FavModel.FavLoc.add(new FavModel(cursor.getInt(0),cursor.getDouble(1),cursor.getDouble(2),cursor.getString(3),
                       cursor.getString(4),cursor.getString(5)));
                Log.i("tag", "loadLocation: " + cursor.getInt(0));

            } while(cursor.moveToNext());
            cursor.close();

            //adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,fvtLocations);
            //listView.setAdapter(adapter);
        }
    }

}