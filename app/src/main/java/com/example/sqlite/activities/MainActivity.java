package com.example.sqlite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.R;
import com.example.sqlite.adapters.MyAdapter;
import com.example.sqlite.database.DBSQLiteHelper;
import com.example.sqlite.models.DataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DBSQLiteHelper DBSQLiteHelper;
    private List<DataModel> notes = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.AddData): {
                startActivityForResult(new Intent(this, AddData.class), 1);
                break;
            }
            case (R.id.DeleteData): {
                DBSQLiteHelper.clearDatabase();
                adapter.notifyDataSetChanged();
                notes.clear();
                break;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                notes = DBSQLiteHelper.getdata();
                adapter.setNotes(notes);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBSQLiteHelper = new DBSQLiteHelper(MainActivity.this);
        notes = DBSQLiteHelper.getdata();
        RecyclerView recyclerView = findViewById(R.id.reView);
        adapter = new MyAdapter(this, notes);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}