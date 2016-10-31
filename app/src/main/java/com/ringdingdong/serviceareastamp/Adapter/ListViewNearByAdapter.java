package com.ringdingdong.serviceareastamp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ringdingdong.serviceareastamp.Data.ListViewNearByItem;
import com.ringdingdong.serviceareastamp.R;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016-08-23.
 */
public class ListViewNearByAdapter extends BaseAdapter {
    private ArrayList<ListViewNearByItem> listViewItemList = new ArrayList<ListViewNearByItem>() ;

    public ListViewNearByAdapter() { }

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
            convertView = inflater.inflate(R.layout.listview_nearby_item, parent, false);
        }

        ImageView imageImageView = (ImageView) convertView.findViewById(R.id.imageView_nearby_image);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.textView_nearby_service_area_name);
        TextView addressTextView = (TextView) convertView.findViewById(R.id.textView_nearby_service_area_address);
        TextView starTextView = (TextView) convertView.findViewById(R.id.textView_nearby_service_area_star);
        RatingBar ratingBar_near_by_star = (RatingBar) convertView.findViewById(R.id.ratingBar_near_by_star);

        ListViewNearByItem listViewItem = listViewItemList.get(position);

        try {
            URL url = new URL(listViewItem.getImageURL());
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imageImageView.setImageBitmap(bm);
        } catch (Exception e) {
        }
        nameTextView.setText(listViewItem.getName());
        addressTextView.setText(listViewItem.getAddress());
        starTextView.setText(listViewItem.getStar());
        try{
            ratingBar_near_by_star.setRating(Float.parseFloat(listViewItem.getStar())/2);
        }catch (NullPointerException e){
            ratingBar_near_by_star.setRating(0);
        }

        if(pos == 0){
            TextView textView_nearby_nearest_item = (TextView) convertView.findViewById(R.id.textView_nearby_nearest_item);
            textView_nearby_nearest_item.setVisibility(View.VISIBLE);
        } else {
            TextView textView_nearby_nearest_item = (TextView) convertView.findViewById(R.id.textView_nearby_nearest_item);
            textView_nearby_nearest_item.setVisibility(View.INVISIBLE);
        }

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

    public void addItem(String imageURL, String name,  String address, String star, String id) {
        ListViewNearByItem item = new ListViewNearByItem();

        item.setImageURL(imageURL);
        item.setName(name);
        item.setAddress(address);
        item.setStar(star);
        item.setCode(id);

        listViewItemList.add(item);
    }
}
