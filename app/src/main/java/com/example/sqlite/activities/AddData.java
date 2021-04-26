package com.example.sqlite.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.R;
import com.example.sqlite.database.DBSQLiteHelper;

import butterknife.BindView;

public class AddData extends AppCompatActivity {
    DBSQLiteHelper dbsqLiteHelper;
    String nameStr, emailStr, numberStr;
    // @BindView(R.id.saveBtn) Button save;
//    @BindView(R.id.Txtname)
//    EditText etname;
//    @BindView(R.id.Txtmail)
//    EditText etemail;
//    @BindView(R.id.Txtnum)
//    EditText etnumber;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        EditText etname = (EditText) findViewById(R.id.Txtname);
        EditText etemail = (EditText) findViewById(R.id.Txtmail);
        EditText etnumber = (EditText) findViewById(R.id.Txtnum);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbsqLiteHelper = new DBSQLiteHelper(this);
        Button save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = etname.getText().toString();
                emailStr = etemail.getText().toString();
                numberStr = etnumber.getText().toString();
                if (!nameStr.isEmpty() && !emailStr.isEmpty() && !numberStr.isEmpty()) {
                    dbsqLiteHelper.insertData(nameStr, emailStr, numberStr);
                    Intent data = new Intent();
                    setResult(Activity.RESULT_OK, data);
                    finish();
                } else {
                    Toast.makeText(AddData.this, "please add details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}