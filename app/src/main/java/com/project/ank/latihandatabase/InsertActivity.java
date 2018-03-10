package com.project.ank.latihandatabase;

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


public class InsertActivity extends AppCompatActivity {

    GetSetClass gsc;
    DBHelper dbHelper;

    Button submitButton;
    EditText nameField, dateField;
    RadioGroup genderGroup;
    RadioButton selectedGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        gsc = new GetSetClass();
        dbHelper = new DBHelper(this);

        nameField = findViewById(R.id.nameField);
        dateField = findViewById(R.id.dateField);
        genderGroup = findViewById(R.id.genderGroup);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int genderId = genderGroup.getCheckedRadioButtonId();
                selectedGender = findViewById(genderId);

                gsc.setArtistName(nameField.getText().toString());
                gsc.setArtistGender(selectedGender.getText().toString());
                gsc.setArtistDOB(dateField.getText().toString());

                long res = dbHelper.insertData(gsc.getArtistName(), gsc.getArtistGender(), gsc.getArtistDOB());

                if (res > 0)
                    Toast.makeText(InsertActivity.this, "Insert Data Success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(InsertActivity.this, "Insert Data Failed", Toast.LENGTH_SHORT).show();

                InsertActivity.this.finish();
            }
        });
        dbHelper.close();
    }
}
