package kr.ac.daegu.app.listedit;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by daeguunivmac26 on 2015. 7. 7..
 */
public class custom_Activity extends ListActivity {




    private final static int New_Code = 0;
    private final static int EDIT_CODE = 1;
    private Context mContext;
    MyListAdapter mAdapter;
        private DatabaseHelper mOpenHelper;

    ArrayList<Alarm> rows = new ArrayList<Alarm>();

    class MyListAdapter extends ArrayAdapter<Alarm> {  //리스트를 표현하기 위한 ArrayAdapter

        ArrayList<Alarm> rows;


        public MyListAdapter(Context context, ArrayList<Alarm> list) {
            super(context, R.layout.alarm_main, list);
            mContext = context;
            rows = list;
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View item = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                item = inflater.inflate(R.layout.alarm_main, null);

            }else{
                item = convertView;
            }
            TextView vTitle = (TextView) item.findViewById(R.id.textView1);
            TextView vYear = (TextView) item.findViewById(R.id.textView2);
            TextView vMonth = (TextView) item.findViewById(R.id.textView3);
            TextView vDay = (TextView) item.findViewById(R.id.textView4);
            TextView vHour = (TextView) item.findViewById(R.id.textView5);
            TextView vMinute = (TextView) item.findViewById(R.id.textView6);

            Alarm user = rows.get(position);

            vTitle.setText(user.title);
            vYear.setText("" + user.year);
            vMonth.setText("" + user.month);
            vDay.setText("" + user.day);
            vHour.setText("" + user.hour);
            vMinute.setText("" + user.minute);


            return item;
        }


    }       // end Activity

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem itemAdd = menu.add(0, 0, Menu.NONE, "새 알람");
        MenuItem itemRem = menu.add(0, 1, Menu.NONE, "종료");

        itemAdd.setIcon(android.R.drawable.ic_menu_add);
        itemAdd.setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:     // NewAlarmActivity 시작
                startActivityForResult( new Intent(this, NewAlarmActivity.class), New_Code);
                return true;
            case 1:     // 종료
                finish();
                return  true;
        }
        return (super.onOptionsItemSelected(item));
    }

       @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

           if(Activity.RESULT_OK == resultCode){
               Bundle extra = data.getExtras();
               Alarm alarm = new Alarm(extra.getInt(DatabaseHelper._ID), extra.getString(DatabaseHelper.TITLE),
                       extra.getInt(DatabaseHelper.YEAR), extra.getInt(DatabaseHelper.MONTH), extra.getInt(DatabaseHelper.DAY),
                       extra.getInt(DatabaseHelper.HOUR), extra.getInt(DatabaseHelper.MINUTE));

               if (requestCode == EDIT_CODE) {
                   mOpenHelper.update(alarm);
               } else {
                   mOpenHelper.insert(alarm);
               }

               mOpenHelper.retriveData(rows);

               mAdapter.notifyDataSetChanged();
           }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //데이터베이스에 적재
        mOpenHelper = new DatabaseHelper(this);
        mOpenHelper.retriveData(rows);

        mAdapter = new MyListAdapter(this, rows);
        setListAdapter(mAdapter);

       getListView().setOnItemClickListener(itemClickListener);

    }


    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent( mContext, NewAlarmActivity.class);
            intent.putExtra(DatabaseHelper._ID, (int)id + 1);
            startActivityForResult( intent, EDIT_CODE);
        }
    };

}
