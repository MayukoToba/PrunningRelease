package com.prunning.owner.prunning;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by owner on 2016/07/10.
 */
@Table(name = "yotei_table")
public class YoteiDB extends Model {

    public YoteiDB(){
        super();
    }



    @Column(name = "subject")
    public String subject;

    @Column(name = "naiyou")
    public String naiyou;

    @Column(name = "start_page")
    public String start_page;

    @Column(name = "finish_page")
    public String finish_page;

    @Column(name = "date")
    public String date;

    @Column(name="end")
    public String end;

    @Column(name="tag")
    public String tag;

    @Override
    public String toString(){
        return subject;
    }

}
