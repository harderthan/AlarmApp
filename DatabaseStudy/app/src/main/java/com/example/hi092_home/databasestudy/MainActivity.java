package com.example.hi092_home.databasestudy;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private DatabaseHelper mOpenHelper;
    ArrayList<Alarm> rows = new ArrayList<Alarm>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenHelper = new DatabaseHelper(this);
//        mOpenHelper.retriveData(rows);

        final EditText editText_id1 = (EditText) findViewById(R.id.editText_id1);
        final View btn_put = (Button) findViewById(R.id.btn_put);

         final EditText editText1 = (EditText) findViewById(R.id.editText1);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);

        EditText editText_id2 = (EditText) findViewById(R.id.editText_id2);
        View btn_search = (Button) findViewById(R.id.btn_search);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        TextView textView6 = (TextView) findViewById(R.id.textView6);

        btn_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = getIntent();
                data.putExtra(DatabaseHelper._ID, editText_id1.getText().toString());
                data.putExtra(DatabaseHelper.TITLE, editText1.getText().toString());
                data.putExtra(DatabaseHelper.YEAR, editText2.getText().toString());
                data.putExtra(DatabaseHelper.MONTH, editText3.getText().toString());
                data.putExtra(DatabaseHelper.DAY, editText4.getText().toString());
                data.putExtra(DatabaseHelper.HOUR, editText5.getText().toString());
                data.putExtra(DatabaseHelper.MINUTE, editText6.getText().toString());

                Bundle extra = data.getExtras();
                Alarm alarm = new Alarm(extra.getInt(DatabaseHelper._ID), extra.getString(DatabaseHelper.TITLE),
                        extra.getInt(DatabaseHelper.YEAR), extra.getInt(DatabaseHelper.MONTH), extra.getInt(DatabaseHelper.DAY),
                        extra.getInt(DatabaseHelper.HOUR), extra.getInt(DatabaseHelper.MINUTE));
                mOpenHelper.insert(alarm);

                Toast.makeText(MainActivity.this, alarm._ID, Toast.LENGTH_LONG).show();;
            }
        });

        View.OnClickListener searchClickLisener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
