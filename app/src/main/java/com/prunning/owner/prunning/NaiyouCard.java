package com.prunning.owner.prunning;

/**
 * Created by owner on 2016/08/10.
 */
public class NaiyouCard {
    public String card_naiyou;
    public String card_start_page;
    public String card_finish_page;
    public String tag;


    public NaiyouCard(String card_naiyou,String card_start_page,String card_finish_page,String tag){
        this.card_naiyou = card_naiyou;
        this.card_start_page = card_start_page;
        this.card_finish_page = card_finish_page;
        this.tag=tag;
    }
}