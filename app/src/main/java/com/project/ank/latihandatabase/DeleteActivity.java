package com.project.ank.latihandatabase;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.project.ank.latihandatabase.DB_Class.DBHelper;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    EditText keyField;
    Button deleteButton;
    ListView dataListView;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        dbHelper = new DBHelper(this);

        keyField = findViewById(R.id.keyField);
        deleteButton = findViewById(R.id.button);
        dataListView = findViewById(R.id.dataListView);

        viewData();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor c = dbHelper.searchKey(Long.parseLong(keyField.getText().toString()));

                if (!c.moveToFirst()) {
                    Toast.makeText(DeleteActivity.this, "Key not found, find another key", Toast.LENGTH_SHORT).show();
                    dbHelper.close();
                    return;
                }

                // delete confirm
                String name = c.getString(1);
                dbHelper.close();

                AlertDialog.Builder dialog = new AlertDialog.Builder(DeleteActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Delete Confirm");
                dialog.setMessage("Are you sure want to delete " + name + " ?");

                // set yes button
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean deleted = dbHelper.deleteData(Long.parseLong(keyField.getText().toString()));
                        
                        if (deleted)
                            Toast.makeText(DeleteActivity.this, "Data has been deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DeleteActivity.this, "Failed to delete data", Toast.LENGTH_SHORT).show();

                        viewData();
                        dbHelper.close();
                    }
                });

                // set no button
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        viewData();
                    }
                });
                dialog.create().show();
            }
        });
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(DeleteActivity.this, android.R.layout.simple_list_item_1, dataList);
        dataListView.setAdapter(adapter);
    }
}
