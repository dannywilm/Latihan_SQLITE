package com.project.ank.latihandatabase;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.project.ank.latihandatabase.DB_Class.DBHelper;
import com.project.ank.latihandatabase.DB_Class.GetSetClass;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{

    DBHelper dbHelper;
    GetSetClass gsc;

    Button searchButton, submitButton;
    EditText idField, nameField, dobField;
    RadioGroup genderGroup;
    RadioButton selectedGender, fGender, mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DBHelper(this);
        gsc = new GetSetClass();

        searchButton = findViewById(R.id.searchButton);
        submitButton = findViewById(R.id.submitButton);
        idField = findViewById(R.id.idField);
        nameField = findViewById(R.id.nameField);
        dobField = findViewById(R.id.dobField);
        genderGroup = findViewById(R.id.genderGroup);
        fGender = findViewById(R.id.femaleChecked);
        mGender = findViewById(R.id.maleChecked);

        searchButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.searchButton:
                searchButtonFunc();
                break;
            case R.id.submitButton:
                submitButtonFunc();
                break;
        }
    }

    public void searchButtonFunc()
    {
        gsc.setArtistId(Integer.parseInt(idField.getText().toString()));

        Cursor c = dbHelper.searchKey(gsc.getArtistId());

        if (c.moveToFirst())
        {
            String gender = c.getString(2);

            if (gender.equals("Female"))
                fGender.setChecked(true);
            else
                mGender.setChecked(true);

            nameField.setText(c.getString(1));
            dobField.setText(c.getString(3));
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Data not Found");
            builder.setTitle("Alert!!");
            builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }
    }

    public void submitButtonFunc()
    {
        String key, name, sex, dob;

        int genderId = genderGroup.getCheckedRadioButtonId();
        selectedGender = findViewById(genderId);

        key = idField.getText().toString();
        name = nameField.getText().toString();
        sex = selectedGender.getText().toString();
        dob = dobField.getText().toString();

        dbHelper.updateData(Long.parseLong(key), name, sex, dob);
        dbHelper.close();
        
        UpdateActivity.this.finish();
        Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
    }
}