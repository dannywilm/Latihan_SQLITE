package com.project.ank.latihandatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.project.ank.latihandatabase.DB_Class.DBHelper;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity implements View.OnClickListener{

    ListView dataListView;
    Button searchButton;
    EditText nameField;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        dbHelper = new DBHelper(this);

        dataListView = findViewById(R.id.dataListView);
        nameField = findViewById(R.id.searchField);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(this);

        viewData();
    }

    public void onClick(View v)
    {
        Cursor c = dbHelper.searchName(nameField.getText().toString());

        ArrayList<String> dataList = new ArrayList<>();

        if (c.moveToFirst())
        {
            do {
                int artistId = c.getInt(0);
                String artistName = c.getString(1);
                String artistGender = c.getString(2);
                String artistDOB = c.getString(3);

                dataList.add(
                        "User ID : " + Integer.toString(artistId) +
                                "\nArtist Name : " + artistName +
                                "\nArtist Gender : " + artistGender +
                                "\nArtist DOB : " + artistDOB
                );
            }
            while(c.moveToNext());
        }
        else
            dataList.add("Data not found");

        dbHelper.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        dataListView.setAdapter(adapter);

    }

    private void viewData()
    {
        Cursor c = dbHelper.getAllData();

        // create array
        ArrayList<String> dataList = new ArrayList<>();

        if (c.moveToFirst())
        {
            do {
                int artistId = c.getInt(0);
                String artistName = c.getString(1);
                String artistGender = c.getString(2);
                String artistDOB = c.getString(3);

                dataList.add(
                                "User ID : " + Integer.toString(artistId) +
                                "\nArtist Name : " + artistName +
                                "\nArtist Gender : " + artistGender +
                                "\nArtist DOB : " + artistDOB
                );

            }
            while(c.moveToNext());
        }

        dbHelper.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        dataListView.setAdapter(adapter);
    }
}
