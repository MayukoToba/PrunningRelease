package com.prunning.owner.prunning;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by owner on 2016/08/08.
 */
public class TaskAdapter extends ArrayAdapter<TaskCard> {
    List<TaskCard> mTaskCard;

    public TaskAdapter(Context context,int layoutResourceId,List<TaskCard> objects){
        super(context,layoutResourceId,objects);

        mTaskCard = objects;
    }

    @Override
    public int getCount(){
        return mTaskCard.size();
    }

    @Override
    public TaskCard getItem(int position){
        return mTaskCard.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.display_card,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final TaskCard item = getItem(position);

        if (item != null){
            //set data
            viewHolder.subjectTextView.setText(item.subject);
            viewHolder.subjectTextView.setTextColor(Color.parseColor(item.mColor));


            viewHolder.naiyouTextView.setText(item.naiyou);
            viewHolder.naiyouTextView.setTextColor(Color.parseColor(item.mColor));
            viewHolder.startpageTextView.setText("(P."+item.start_page+"~");
            viewHolder.startpageTextView.setTextColor(Color.parseColor(item.mColor));
            viewHolder.finishpageTextView.setText("P."+item.finish_page+")");
            viewHolder.finishpageTextView.setTextColor(Color.parseColor(item.mColor));


            Log.e("TAGの値",item.tag);
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


        }



        return convertView;
    }

    private class ViewHolder{
        TextView subjectTextView,naiyouTextView,
                startpageTextView,finishpageTextView;
        ImageView imageView;

        public ViewHolder(View view){
            //get instance
            subjectTextView = (TextView) view.findViewById(R.id.subject_textView);
            naiyouTextView = (TextView)view.findViewById(R.id.naiyou_textView);
            startpageTextView = (TextView)view.findViewById(R.id.startpage_textView);
            finishpageTextView = (TextView)view.findViewById(R.id.finishpage_textView);
            imageView =(ImageView)view.findViewById(R.id.imageView3);

        }

    }

}
