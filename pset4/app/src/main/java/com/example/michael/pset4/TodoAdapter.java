package com.example.michael.pset4;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Michael on 23-11-2017.
 */

public class TodoAdapter extends ResourceCursorAdapter {

    public TodoAdapter(Context context, Cursor cursor) {
        super(context, R.layout.row_todo, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int myName =  cursor.getColumnIndex("title");
        String myInt = cursor.getString(myName);

        int myName2 =  cursor.getColumnIndex("completed");
        int myInt2 = cursor.getInt(myName2);

        CheckBox cb1 = view.findViewById(R.id.checkBox);
        if(myInt2 == 0) {
            cb1.setChecked(true);
        }
        else {
            cb1.setChecked(false);
        }

        TextView tv1 = view.findViewById(R.id.textView2);
        tv1.setText(myInt);
    }
}
