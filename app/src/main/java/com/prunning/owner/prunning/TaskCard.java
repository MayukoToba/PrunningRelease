package com.prunning.owner.prunning;

/**
 * Created by owner on 2016/08/08.
 */
public class TaskCard {
    public String subject;
    public String naiyou;
    public String start_page;
    public String finish_page;
    public String mColor;
    public String tag;

    public TaskCard(String subject,String naiyou,String start_page,String finish_page,String mColor,String tag){
        this.subject = subject;
        this.naiyou = naiyou;
        this.start_page = start_page;
        this.finish_page = finish_page;
        this.mColor = mColor;
        this.tag = tag;
    }

}
