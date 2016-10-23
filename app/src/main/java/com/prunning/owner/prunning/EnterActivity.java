package com.prunning.owner.prunning;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EnterActivity extends AppCompatActivity implements CheckBox.OnClickListener{

    private ArrayList<String> mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    EditText subject_edit, naiyou_edit, page_first_edit, page_second_edit;

    int yotei_year,yotei_monthOfYear,yotei_dayOfMonth;
    String yotei_month,yotei_date;
    TextView enter_ok_textView;

    YoteiDB mYoteiDB;

    DatePickerDialog datePickerDialog;
    String  tag = "";
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;


    DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(android.widget.DatePicker datePicker, int getYear,
                              int getMonthOfYear, int getDayOfMonth) {

            yotei_year = getYear;
            yotei_monthOfYear = getMonthOfYear;
            yotei_dayOfMonth = getDayOfMonth;

            int monthOfYar=yotei_monthOfYear+1;
            if (monthOfYar<=9){
                yotei_month="0" +String.valueOf(monthOfYar);
            }else{
                yotei_month=String.valueOf(monthOfYar);
            }
            if (yotei_dayOfMonth<=9){
                yotei_date="0" +String.valueOf(yotei_dayOfMonth);
            }else{
                yotei_date=String.valueOf(yotei_dayOfMonth);
            }
            Log.d("yotei_month",""+yotei_month);



        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPlanetTitles = new ArrayList<String>();

        mDrawerList = (ListView)findViewById(R.id.listView);

        List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
        for (YoteiDB i : items) {
            String subject;
            subject= i.subject;
            if(!mPlanetTitles.contains(i.subject)){
                mPlanetTitles.add(subject);
            }

        }

        SubjectAdapter arrayAdapter= new SubjectAdapter (this, R.layout.card,mPlanetTitles);
        mDrawerList.setAdapter(arrayAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent,View v,int position,long id){
                String subject;
                subject = mPlanetTitles.get(position);
                Intent intent =new Intent (getApplicationContext(),NaiyouListActivity.class);
                intent.putExtra("科目",subject);
                startActivity(intent);

                //mTaskAdapter.notifyDataSetChanged();

            }
        });


        page_first_edit = (EditText) findViewById(R.id.page_first_edit);
        page_second_edit = (EditText) findViewById(R.id.page_second_edit);
        subject_edit = (EditText) findViewById(R.id.subject_edit);
        naiyou_edit = (EditText) findViewById(R.id.naiyou_edit);

        enter_ok_textView = (TextView)findViewById(R.id.enter_ok_textView);


        mYoteiDB = new YoteiDB();



        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("入力");

        setSupportActionBar(mToolbar);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            public void onDrawerClosed(View drawerView) {
            }


            public void onDrawerOpened(View drawerView) {

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        icon();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    public void calender(View v) {

        //日付初期値設定
        Calendar calendar = Calendar.getInstance();
        yotei_year = calendar.get(Calendar.YEAR); // 年
        yotei_monthOfYear = calendar.get(Calendar.MONTH) ;// 月


        yotei_dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);// 日

        // 日付設定ダイアログの作成・リスナの登録
        datePickerDialog = new DatePickerDialog(this, DateSetListener, yotei_year, yotei_monthOfYear, yotei_dayOfMonth);


        datePickerDialog.show();

    }

    public void setting(View v) {
        Intent intent = new Intent(this, TestDateActivity.class);
        startActivity(intent);
    }

    public void ok_enter(View v) {
        if (page_first_edit.getText().toString().equals("") ||
                page_second_edit.getText().toString().equals("") ||
                subject_edit.getText().toString().equals("") ||
                naiyou_edit.getText().toString().equals("") ) {


            enter_ok_textView.setText("入力されていない箇所があります。");

        } else {
            saveYotei();

            enter_ok_textView.setText(null);
            page_first_edit.setText("");
            page_second_edit.setText("");
            subject_edit.setText("");
            naiyou_edit.setText("");





            List<YoteiDB> items2 = new Select().from(YoteiDB.class).execute();
            for (YoteiDB i : items2) {

                Log.d("oncreate subjet",""+i.subject);
                Log.d("oncreate naiyou",""+i.naiyou);
                Log.d("oncreate start_page",""+i.start_page);
                Log.d("oncreate finish_page",""+i.finish_page);



            }







            Intent intent =new Intent (getApplicationContext(),DisplayActivity.class);
            startActivity(intent);



        }

    }

    void icon(){
         checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
         checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
         checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
         checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
         checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
         checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        // チェックボックスのチェック状態を設定します
        checkBox1.setChecked(true);
        tag = "1";
        // チェックボックスがクリックされた時に呼び出されるコールバックリスナーを登録します
        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBox5.setOnClickListener(this);
        checkBox6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){

        //処理
        tag = (String) v.getTag();
        Log.e("TAG",tag);


        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        checkBox5.setChecked(false);
        checkBox6.setChecked(false);
        if(tag.equals("1")){
            checkBox1.setChecked(true);
        }else if(tag.equals("2")){
            checkBox2.setChecked(true);
        }else if(tag.equals("3")){
            checkBox3.setChecked(true);
        }else if(tag.equals("4")){
            checkBox4.setChecked(true);
        }else if(tag.equals("5")){
            checkBox5.setChecked(true);
        }else if(tag.equals("6")){
            checkBox6.equals(true);
        }



    }





    void saveYotei(){
        mYoteiDB.start_page = page_first_edit.getText().toString();
        mYoteiDB.finish_page = page_second_edit.getText().toString();
        mYoteiDB.subject = subject_edit.getText().toString();
        mYoteiDB.naiyou =naiyou_edit.getText().toString() ;
        mYoteiDB.end = "nonend";
        String date = String.valueOf(yotei_year)+"/"+yotei_month+"/"+yotei_date;
        Log.d("naiyou_year",""+date);
        Log.d("end",""+mYoteiDB.end);
        mYoteiDB.date =date;
        mYoteiDB.tag = tag;
        Log.d("tag",mYoteiDB.tag);
        mYoteiDB.save();
    }
}



