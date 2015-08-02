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


    DatabaseHelper mOpenHelper;
   // ArrayList<Alarm> rows = new ArrayList<Alarm>();

    int item_id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newalarm_layout);

        mOpenHelper = new DatabaseHelper(this);


        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);

        final EditText editText1 = (EditText) findViewById(R.id.editText1);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);

                // 수정 요청 여부
            Intent data = getIntent();
        if(data != null) {
            TextView txtTop = (TextView) findViewById(R.id.txtLog);
            txtTop.setText("edit alarm");
            item_id = data.getIntExtra( DatabaseHelper._ID, -1);
            Alarm alarm = mOpenHelper.retrive(item_id);
            if (alarm != null) {
                editText1.setText(alarm.title);
                editText2.setText(""+alarm.year);
                editText3.setText(alarm.month+"");
                editText4.setText(alarm.day+"");
                editText5.setText(alarm.hour+"");
                editText6.setText(alarm.minute+"");

            }
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = getIntent();
                data.putExtra(DatabaseHelper.TITLE, editText1.getText().toString());
                data.putExtra(DatabaseHelper.YEAR, editText2.getText().toString());
                data.putExtra(DatabaseHelper.MONTH, editText3.getText().toString());
                data.putExtra(DatabaseHelper.DAY, editText4.getText().toString());
                data.putExtra(DatabaseHelper.HOUR, editText5.getText().toString());
                data.putExtra(DatabaseHelper.MINUTE, editText6.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


    }
}
