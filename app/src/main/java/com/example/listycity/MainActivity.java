package com.example.listycity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button add_city;
    Button remove_city;

    LinearLayout add_city_form;
    TextInputLayout add_city_input;
    EditText user_input;
    Button confirm_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Add the list of cities to the app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String [] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // add a new city
        add_city_form = findViewById(R.id.add_city_form);
        add_city_input = findViewById(R.id.add_city_input);
        user_input = findViewById(R.id.user_input);
        confirm_button = findViewById(R.id.confirm_button);

        add_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle_visibility(add_city_form);
            }
        });


        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city_name = user_input.getText().toString();
                dataList.add(city_name);
                cityAdapter.notifyDataSetChanged();
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                remove_city.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataList.remove(position);
                        cityAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


    // Toggle the visibility of the add_city_form
    public void toggle_visibility(LinearLayout layout) {
        if (layout.getVisibility() == VISIBLE) {
            layout.setVisibility(VISIBLE);
        } else {
            layout.setVisibility(GONE);
        }
    }
}