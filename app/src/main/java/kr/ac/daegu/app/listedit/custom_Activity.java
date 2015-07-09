package kr.ac.daegu.app.listedit;

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
public class custom_Activity extends Activity{

    ArrayList<MyUser> rows = new ArrayList<MyUser>();

    class MyUser {
        String name;
        String address;
        String intro;
        int url;

        String getAddress(){
            return address;
        }


        public MyUser(String t0,String t1, String t2, int t3) {
            this.intro = t0;
            this.name = t1;
            this.address = t2;
            this.url = t3;
        }
    }

    class MyUserAdapter extends ArrayAdapter<MyUser> {



        public MyUserAdapter(ArrayList<MyUser> objects){
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
                item = inflater.inflate(R.layout.custom_listview, null);

            }else{
                item = convertView;
            }
            ImageView img = (ImageView) item.findViewById(R.id.imageView1);
            TextView vName =(TextView) item.findViewById(R.id.textView1);
            TextView vTime =(TextView) item.findViewById(R.id.textView2);
            TextView vDesc =(TextView) item.findViewById(R.id.textView3);

            MyUser user = getItem(position);

            img.setImageResource(user.url);
            vName.setText(user.name);
            vTime.setText(user.address);
            vDesc.setText(user.intro);


            return item;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.list);

        ArrayList<MyUser> data = retrieveData();
        MyUserAdapter adapter = new MyUserAdapter(data);

        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener listItemClick = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyUser user = rows.get(position);

                Toast.makeText(getBaseContext(), user.getAddress(), Toast.LENGTH_SHORT).show();

            }
        };

        listView.setOnItemClickListener(listItemClick);


    }

    @Override
    protected void onResume() {super.onResume(); }

    public ArrayList<MyUser> retrieveData(){
        ArrayList<MyUser> list = new ArrayList<MyUser>();




        list.add(
                new MyUser(
                        "서현",
                        "2s",
                        "안녕하세요",
                        R.drawable.profile_1

                )
        );
        list.add(
                new MyUser(
                        "서현",
                        "2s",
                        "안녕하세요",
                        R.drawable.profile_2
                )
        );
        list.add(
                new MyUser(
                        "서현",
                        "2s",
                        "안녕하세요",
                        R.drawable.profile_3
                )
        );
        list.add(
                new MyUser(
                        "서현",
                        "2s",
                        "안녕하세요",
                        R.drawable.profile_4
                )
        );


        return list;
    }

}
