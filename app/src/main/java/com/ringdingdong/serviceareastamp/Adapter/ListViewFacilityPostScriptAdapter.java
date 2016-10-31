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

import com.ringdingdong.serviceareastamp.Data.ListViewFacilityPostScriptItem;
import com.ringdingdong.serviceareastamp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-08-23.
 */
public class ListViewFacilityPostScriptAdapter extends BaseAdapter {
    private ArrayList<ListViewFacilityPostScriptItem> listViewItemList = new ArrayList<ListViewFacilityPostScriptItem>() ;

    public ListViewFacilityPostScriptAdapter() { }

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
            convertView = inflater.inflate(R.layout.listview_facility_post_script_item, parent, false);
        }

        ImageView imageImageView = (ImageView) convertView.findViewById(R.id.imageView_facility_post_script_profile_image);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.textView_facility_post_script_name);
        TextView memoTextView = (TextView) convertView.findViewById(R.id.textView_facility_post_script_memo);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.textView_facility_post_script_date);
        TextView tag1TextView = (TextView) convertView.findViewById(R.id.textView_facility_post_script_tag1);
        TextView tag2TextView = (TextView) convertView.findViewById(R.id.textView_facility_post_script_tag2);
        TextView tag3TextView = (TextView) convertView.findViewById(R.id.textView_facility_post_script_tag3);

        ListViewFacilityPostScriptItem listViewItem = listViewItemList.get(position);

        imageImageView.setImageDrawable(listViewItem.getProfileimage());
        nameTextView.setText(listViewItem.getName());
        memoTextView.setText(listViewItem.getMemo());
        dateTextView.setText(listViewItem.getDate());
        tag1TextView.setText(listViewItem.getTag1());
        tag2TextView.setText(listViewItem.getTag2());
        tag3TextView.setText(listViewItem.getTag3());

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

    public void addItem(Drawable image, String memo, String name, String tag1, String tag2, String tag3, String date) {
        ListViewFacilityPostScriptItem item = new ListViewFacilityPostScriptItem();

        item.setProfileimage(image);
        item.setName(name);
        item.setMemo(memo);
        item.setDate(date);
        item.setTag1(tag1);
        item.setTag2(tag2);
        item.setTag3(tag3);

        listViewItemList.add(item);
    }
}
