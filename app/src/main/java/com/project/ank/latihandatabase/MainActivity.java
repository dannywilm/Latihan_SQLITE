package com.project.ank.latihandatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button showAct, insertAct, updateAct, deleteAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showAct = findViewById(R.id.showAct);
        insertAct = findViewById(R.id.insertAct);
        updateAct = findViewById(R.id.updateAct);
        deleteAct = findViewById(R.id.deleteAct);

        showAct.setOnClickListener(this);
        insertAct.setOnClickListener(this);
        updateAct.setOnClickListener(this);
        deleteAct.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent i;

        switch (v.getId())
        {
            case R.id.showAct:
                i = new Intent(this, ShowDataActivity.class);
                startActivity(i);
                break;
            case R.id.insertAct:
                i = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(i);
                break;
            case R.id.updateAct:
                i = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(i);
                break;
            case R.id.deleteAct:
                i = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(i);
                break;
            default:
                Toast.makeText(this, "Button not Available", Toast.LENGTH_SHORT).show();
        }
    }
}
