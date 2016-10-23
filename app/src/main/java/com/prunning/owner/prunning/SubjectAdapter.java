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
 * Created by owner on 16/05/29.
 */
public class SubjectAdapter extends ArrayAdapter<String> {
    List<String> mstring;



    public SubjectAdapter(Context context,int layoutResourced,List<String> objects){
        super(context, layoutResourced,objects);

        mstring = objects;
    }

    @Override
    public int getCount(){
        return  mstring.size();
    }

    @Override
    public String getItem(int position){
        return mstring.get(position);
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final String item = getItem(position);

        if (item !=null){
            //set data
            viewHolder.titleTextView.setText(item);
        }




        return convertView;
    }

    private  class ViewHolder{
        TextView titleTextView;
        ImageView imageView;
        public ViewHolder(View v){
            titleTextView = (TextView)v.findViewById(R.id.subject_title_textView);
            imageView = (ImageView)v.findViewById(R.id.imageView);
        }

    }
}
