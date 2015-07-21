package kr.ac.daegu.app.listedit;

import android.app.ListActivity;
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
public class custom_Activity extends Activity {

    ArrayList<Alarm> rows = new ArrayList<Alarm>();


    private final static int New_Code = 0;
    private final static int EDIT_CODE = 1;


    private DatabaseHelper mOpenHelper;

    class MyUserAdapter extends ArrayAdapter<Alarm> {  //리스트를 표현하기 위한 ArrayAdapter

        public MyUserAdapter(ArrayList<Alarm> objects){
            super(getBaseContext(), 0, objects);
            rows = objects;
        }

        @Override
        public int getCount(){ return rows.size();}

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View item = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getBaseContext());
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

            Alarm user = getItem(position);

            vTitle.setText(user.title);
            vYear.setText("" + user.year);
            vMonth.setText("" + user.month);
            vDay.setText("" + user.day);
            vHour.setText("" + user.hour);
            vMinute.setText("" + user.minute);


            return item;
        }


    }

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
            case 0:
                startActivityForResult( new Intent(this, NewAlarmActivity.class), New_Code);
                return true;
            case 1:
                finish();
                return  true;
        }
        return (super.onOptionsItemSelected(item));
    }

       @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           if(Activity.RESULT_OK == resultCode){
               if (requestCode == EDIT_CODE) {

               }
           }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenHelper = new DatabaseHelper(this);
       // mOpenHelper.retriveData(rows);

        ListView listView = (ListView)findViewById(R.id.list);

        ArrayList<Alarm> data = retrieveData();

        MyUserAdapter adapter = new MyUserAdapter(data);

        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener listItemClick = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alarm user = rows.get(position);

                Toast.makeText(getBaseContext(), user.getAlarm(), Toast.LENGTH_SHORT).show();

            }
        };

        listView.setOnItemClickListener(listItemClick);


    }

    @Override
    protected void onResume() {super.onResume(); }

    public ArrayList<Alarm> retrieveData(){
        ArrayList<Alarm> list = new ArrayList<Alarm>();

        list.add(
                new Alarm(
                    1, "첫번째", 3, 4, 5, 6, 7
                )
        );
        list.add(
                new Alarm(
                    1, "두번째", 3, 4, 5, 6, 7
                )
        );
        list.add(
                new Alarm(
                    1, "세번째", 3, 4, 5, 6, 7
                )
        );
        list.add(
                new Alarm(
                    1, "네번째", 3, 4, 5, 6, 7
                )
        );
        list.add(
                new Alarm(
                    1, "다섯번째", 3, 4, 5, 6, 7
                )
        );
       list.add(
                new Alarm(
                    1, "여섯번째", 3, 4, 5, 6, 7
                )
        );
       list.add(
                new Alarm(
                    1, "일곱번째", 3, 4, 5, 6, 7
                )
        );
       list.add(
                new Alarm(
                    1, "여덟번째", 3, 4, 5, 6, 7
                )
        );
       list.add(
                new Alarm(
                    1, "아홉번째", 3, 4, 5, 6, 7
                )
        );




        return list;
    }

}
