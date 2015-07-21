package kr.ac.daegu.app.listedit;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hi092_home on 2015-07-19.
 */
public class NewAlarmActivity  extends Activity {

    EditText year,month,day,hour,minute;
    DatabaseHelper mOpenHelper;
   // ArrayList<Alarm> rows = new ArrayList<Alarm>();

    int item_id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newalarm_layout);

        mOpenHelper = new DatabaseHelper(this);
       // mOpenHelper.retriveData(rows);

        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);

        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        EditText editText4 = (EditText) findViewById(R.id.editText4);
        EditText editText5 = (EditText) findViewById(R.id.editText5);
        EditText editText6 = (EditText) findViewById(R.id.editText6);

        Intent data = getIntent();
        if(data != null) {
            TextView txtTop = (TextView) findViewById(R.id.txtLog);
            txtTop.setText("");
            item_id = data.getIntExtra( DatabaseHelper._ID, -1);
            Alarm alarm = mOpenHelper.retrive(item_id);
            if (alarm != null) {
                year.setText(alarm.year);
                month.setText(alarm.month);
                day.setText(alarm.day);
                hour.setText(alarm.hour);
                minute.setText(alarm.minute);

            }
        }
//
//        btn_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent data = getIntent();
//                data.putExtra(DatabaseHelper.TITLE, editText1.getText().toString());
//                data.putExtra(DatabaseHelper.YEAR, editText2.getText().toString());
//                data.putExtra(DatabaseHelper.MONTH, editText3.getText().toString());
//                data.putExtra(DatabaseHelper.DAY, editText4.getText().toString());
//                data.putExtra(DatabaseHelper.HOUR, editText5.getText().toString());
//                data.putExtra(DatabaseHelper.MINUTE, editText6.getText().toString());
//                setResult(RESULT_OK, data);
//                finish();
//            }
//        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


/*

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (Activity.RESULT_OK == resultCode) {
                Bundle extra = data.getExtras();
                Alarm alarm = new Alarm(extra.getInt(DatabaseHelper._ID), extra.getString(DatabaseHelper.TITLE),
                        extra.getInt(DatabaseHelper.YEAR), extra.getInt(DatabaseHelper.MONTH), extra.getInt(DatabaseHelper.DAY),
                        extra.getInt(DatabaseHelper.HOUR), extra.getInt(DatabaseHelper.MINUTE));

                if (requestCode == -1 ) {
                    mOpenHelper.update(alarm);
                } else {
                    mOpenHelper.insert(alarm);
                }
                mOpenHelper.retriveData(rows);

                }
            }
        }

*/


    }
}
