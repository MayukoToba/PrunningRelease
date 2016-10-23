package com.prunning.owner.prunning;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private ArrayList<String> mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    TextView dateText;
    TextView countDateText;
    TextView countTimeText;
    TextView display_textView;

    SharedPreferences pref1, pref2;

    Calendar nCalendar, tCalendar;

    YoteiDB mYoteiBD;

    List<TaskCard> taskCardList;
    TaskAdapter mTaskAdapter;
    ListView mlistView;


    int year;
    int monthOfYear;
    int dayOfMonth;

    int nowYear;
    int nowMonth;
    int nowDay;


    java.util.Date utilStartDate;
    java.sql.Date sqlStartDate;
    java.util.Date utilToDate;
    java.sql.Date sqlToDate;

    boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        SharedPreferences data = getSharedPreferences("pref_test", Context.MODE_PRIVATE);
        isFirst = data.getBoolean("isFirst", true);


        mlistView = (ListView) findViewById(R.id.todo_list);
        taskCardList = new ArrayList<TaskCard>();


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPlanetTitles = new ArrayList<String>();

        mDrawerList = (ListView) findViewById(R.id.listView);


        List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
        for (YoteiDB i : items) {
            String subject;
            subject = i.subject;
            if (!mPlanetTitles.contains(i.subject)) {
                mPlanetTitles.add(subject);
            }


        }

        SubjectAdapter arrayAdapter = new SubjectAdapter(this, R.layout.card, mPlanetTitles);
        mDrawerList.setAdapter(arrayAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String subject;
                subject = mPlanetTitles.get(position);
                Intent intent = new Intent(getApplicationContext(), NaiyouListActivity.class);
                intent.putExtra("科目", subject);
                startActivity(intent);

                //mTaskAdapter.notifyDataSetChanged();

            }
        });


        pref1 = getSharedPreferences("pref_test", MODE_PRIVATE);
        pref2 = getSharedPreferences("pref_card", MODE_PRIVATE);


        SubjectAdapter subjectAdapter = new SubjectAdapter(this,
                R.layout.card, mPlanetTitles);

        // Set the adapter for the list view
        mDrawerList.setAdapter(subjectAdapter);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("表示");

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

        dateText = (TextView) findViewById(R.id.dateView);
        countDateText = (TextView) findViewById(R.id.countDateView);
        countTimeText = (TextView) findViewById(R.id.countTimeView);
        display_textView = (TextView) findViewById(R.id.display_textView);

        nowDate();
        show();

        /*List<YoteiDB> items1 = new Select().from(YoteiDB.class).execute();
        for (YoteiDB i : items1) {
            i.delete();
        }*/

        if (isFirst) {

            Intent intet = new Intent(this, TestDateActivity.class);
            startActivity(intet);
        }


    }

    void nowDate() {
        nCalendar = Calendar.getInstance();
        nowYear = nCalendar.get(Calendar.YEAR); // 年
        nowMonth = nCalendar.get(Calendar.MONTH) + 1; // 月
        nowDay = nCalendar.get(Calendar.DAY_OF_MONTH); // 日
    }

    void show() {
        String title = pref1.getString("title", "テスト頑張ってね");
        year = pref1.getInt("year", 0);
        monthOfYear = pref1.getInt("month", 0) + 1;
        dayOfMonth = pref1.getInt("day", 0);


        //初期状態だった時現在日時を表示する
        if (year == 0 && monthOfYear == 1 && dayOfMonth == 0) {
            display_textView.setText("テスト頑張ってね");
            dateText.setText(nowYear + "/" + nowMonth + "/" + nowDay);
        }
        //年を設定した時
        else {
            display_textView.setText(title + "");
            dateText.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
            Log.d("test", year + "年" + monthOfYear + "月" + dayOfMonth + "日");

            tCalendar = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            // 日付を作成します。
            try {
                utilStartDate = sdf.parse(nowYear + "/" + nowMonth + "/" + nowDay);
                sqlStartDate = new java.sql.Date(utilStartDate.getTime());

                utilToDate = sdf.parse(year + "/" + monthOfYear + "/" + dayOfMonth);
                sqlToDate = new java.sql.Date(utilToDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            // 日付をlong値に変換します。
            long dateTimeTo = sqlToDate.getTime();
            long dateTimeFrom = sqlStartDate.getTime();

            // 差分の日数を算出します。
            long dayDiff = (dateTimeTo - dateTimeFrom) / (1000 * 60 * 60 * 24);

            countDateText.setVisibility(View.VISIBLE);
            countDateText.setText("あと" + dayDiff + "日");
            countTimeText.setVisibility(View.VISIBLE);
        }

        setYotei();


        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {


                new AlertDialog.Builder(DisplayActivity.this)
                        .setTitle("終了")
                        .setMessage("課題が終わりましたか？")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                taskCardList.get(position).mColor = ("#0000FF");
                                mTaskAdapter.notifyDataSetChanged();

                                String subject = taskCardList.get(position).subject;
                                String naiyou = taskCardList.get(position).naiyou;
                                String start_page = taskCardList.get(position).start_page;
                                String finish_page = taskCardList.get(position).finish_page;

                                //ここに処理を書く

                                List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
                                for (YoteiDB i : items) {
                                    nowDate();

                                    if (i.subject.equals(subject) && i.naiyou.equals(naiyou) && i.start_page.equals(start_page) && i.finish_page.equals(finish_page)) {
                                        i.end = "end";
                                        i.save();
                                        Log.d("end", "" + i.end);

                                    }


                                }


                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                Log.d("naiyou_date", "click");


            }
        });


        mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //ここに処理を書く
                String subject = taskCardList.get(position).subject;
                String naiyou = taskCardList.get(position).naiyou;
                String start_page = taskCardList.get(position).start_page;
                String finish_page = taskCardList.get(position).finish_page;

                //ここに処理を書く

                List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
                for (YoteiDB i : items) {
                    nowDate();

                    if (i.subject.equals(subject) && i.naiyou.equals(naiyou) && i.start_page.equals(start_page) && i.finish_page.equals(finish_page)) {
                        taskCardList.remove(position);
                        mTaskAdapter.notifyDataSetChanged();

                        i.delete();
                    }


                }


                return false;
            }
        });


    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void setting(View v) {
        Intent intent = new Intent(this, TestDateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    void setYotei() {


        List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
        for (YoteiDB i : items) {
            nowDate();

            // String nowDate = String.valueOf(nowYear) + "/" + String.valueOf(nowMonth) + "/" + String.valueOf(nowDay);
            int naiyou_year = Integer.parseInt(i.date.substring(0, 4));
            int naiyou_month = Integer.parseInt(i.date.substring(5, 7));
            int naiyou_date = Integer.parseInt(i.date.substring(8, 10));

            Log.d("nowYear", "" + nowYear);
            Log.d("nowMonth", "" + nowMonth);
            Log.d("nowDay", "" + nowDay);

            Log.d("naiyou_year", "" + naiyou_year);
            Log.d("naiyou_month", "" + naiyou_month);
            Log.d("naiyou_date", "" + naiyou_date);

            if (naiyou_year < nowYear || naiyou_month < nowMonth || naiyou_date < nowDay) {
                if (i.end.equals("end")) {
                    Log.d("if end", "" + i.subject);
                    i.delete();
                } else if (i.end.equals("nonend")) {
                    Log.d("if", "" + i.subject);
                    TaskCard mTaskCard;
                    mTaskCard = new TaskCard(i.subject, i.naiyou, i.start_page, i.finish_page, "#FF0000", i.tag);
                    taskCardList.add(mTaskCard);

                } else {
                    Log.d("end else", "" + i.subject);
                }
            } else if (naiyou_year == nowYear && naiyou_month == nowMonth && naiyou_date == nowDay) {
                if (i.end.equals("nonend")) {
                    Log.d("else if", "" + i.subject);
                    TaskCard mTaskCard;
                    mTaskCard = new TaskCard(i.subject, i.naiyou, i.start_page, i.finish_page, "#000000", i.tag);
                    taskCardList.add(mTaskCard);

                } else if (i.end.equals("end")) {
                    TaskCard mTaskCard;
                    mTaskCard = new TaskCard(i.subject, i.naiyou, i.start_page, i.finish_page, "#0000FF", i.tag);
                    taskCardList.add(mTaskCard);


                }
            }


        }

        mTaskAdapter = new TaskAdapter(this, R.layout.display_card, taskCardList);
        mlistView.setAdapter(mTaskAdapter);

    }

    public void enter(View v) {
        Intent intent = new Intent(this, EnterActivity.class);
        startActivity(intent);
    }


    @Override
    public void onPause() {
        super.onPause();


    }


}
