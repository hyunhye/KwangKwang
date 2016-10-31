package com.ringdingdong.serviceareastamp.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ringdingdong.serviceareastamp.Data.ListViewFoodPostScriptItem;
import com.ringdingdong.serviceareastamp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-08-23.
 */
public class ListViewFoodPostScriptAdapter extends BaseAdapter {
    private ArrayList<ListViewFoodPostScriptItem> listViewItemList = new ArrayList<ListViewFoodPostScriptItem>() ;

    public ListViewFoodPostScriptAdapter() { }

    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_food_post_script_item, parent, false);
        }

        ImageView imageImageView = (ImageView) convertView.findViewById(R.id.imageView_food_post_script_profile_image);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.textView_food_post_script_name);
        TextView memoTextView = (TextView) convertView.findViewById(R.id.textView_food_post_script_memo);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.textView_food_post_script_date);
        RatingBar starRatingBar = (RatingBar) convertView.findViewById(R.id.ratingBar_food_post_script_star);

        ListViewFoodPostScriptItem listViewItem = listViewItemList.get(position);

        imageImageView.setImageDrawable(listViewItem.getProfileimage());
        nameTextView.setText(listViewItem.getName());
        memoTextView.setText(listViewItem.getMemo());
        dateTextView.setText(listViewItem.getDate());
        starRatingBar.setRating(Integer.parseInt(listViewItem.getStar()));

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    public void addItem(Drawable image, String memo, String name, String star, String date) {
        ListViewFoodPostScriptItem item = new ListViewFoodPostScriptItem();

        item.setProfileimage(image);
        item.setName(name);
        item.setMemo(memo);
        item.setDate(date);
        item.setStar(star);

        listViewItemList.add(item);
    }
}
