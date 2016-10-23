package com.prunning.owner.prunning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by owner on 2016/08/10.
 */
public class NaiyouAdapter extends ArrayAdapter<NaiyouCard>{
    List<NaiyouCard> mNaiyou;

    public NaiyouAdapter(Context context,int layoutResorceId, List<NaiyouCard> objects){
        super (context,layoutResorceId,objects);

        mNaiyou = objects;

    }

    @Override
    public int getCount(){
        return mNaiyou.size();
    }

    @Override
    public NaiyouCard getItem(int position){
        return mNaiyou.get(position);
    }

    public View getView(final int position, View convertVIew, ViewGroup parent){
        final ViewHolder viewHolder;

        if(convertVIew ==null){
            convertVIew= LayoutInflater.from(getContext()).inflate(R.layout.naiyou_card,null);
            viewHolder = new ViewHolder(convertVIew);
            convertVIew.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertVIew.getTag();
        }

        final NaiyouCard item = getItem(position);

        if (item != null){
            //set data
            viewHolder.naiyou_textView.setText(item.card_naiyou);
            viewHolder.naiyou_start_page_textView.setText(item.card_start_page);
            viewHolder.naiyou_finish_page_textView.setText(item.card_finish_page);
        }

        if(item.tag.equals("1")){
            viewHolder.imageView.setImageResource(R.drawable.icon_bezyu);
        }else if(item.tag.equals("2")){
            viewHolder.imageView.setImageResource(R.drawable.icon_purple);
        }else if(item.tag.equals("3")){
            viewHolder.imageView.setImageResource(R.drawable.icon_yellow);
        }else if(item.tag.equals("4")){
            viewHolder.imageView.setImageResource(R.drawable.icon_blue);
        }else if(item.tag.equals("5")){
            viewHolder.imageView.setImageResource(R.drawable.icon_green);
        }else if(item.tag.equals("6")){
            viewHolder.imageView.setImageResource(R.drawable.icon_pink);
        }

        return convertVIew;
    }

    private class ViewHolder{
        TextView naiyou_textView;
        TextView naiyou_start_page_textView;
        TextView naiyou_finish_page_textView;
        ImageView imageView;

        public  ViewHolder(View view){
            //get instance
            naiyou_textView = (TextView) view.findViewById(R.id.naiyou);
            naiyou_start_page_textView = (TextView) view.findViewById(R.id.startpage);
            naiyou_finish_page_textView = (TextView) view.findViewById(R.id.finishpage);
            imageView = (ImageView)view.findViewById(R.id.imageView4);
        }
    }
}
