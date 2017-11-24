package com.example.michael.pset4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private TodoAdapter adapter;
    private TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = TodoDatabase.getInstance(getApplicationContext());

        ListView yourListView = findViewById(R.id.listView1);
        adapter = new TodoAdapter(getApplicationContext(), db.selectAll());

        yourListView.setAdapter(adapter);
        yourListView.setOnItemClickListener(new onClickList());
        yourListView.setOnItemLongClickListener(new onClickList2());
    }

    public void addItem(View view) {
        db = TodoDatabase.getInstance(getApplicationContext());

        EditText et = findViewById(R.id.editText3);
        db.insert(et.getText().toString(), 1);
        updateData();
    }

    private void updateData() {
        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
    }

    private class onClickList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ListView listview = findViewById(R.id.listView1);

            int position = listview.getPositionForView(view);
            int completed = 0;

            db.update(position + 1, completed);
            updateData();
        }
    }


    private class onClickList2 implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            ListView listview = findViewById(R.id.listView1);
            int position = listview.getPositionForView(view);

            db.delete(position+ 1);
            updateData();
            return false;
        }
    }


}
