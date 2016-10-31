package com.ringdingdong.serviceareastamp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ringdingdong.serviceareastamp.Data.ChildListExpressStampItem;
import com.ringdingdong.serviceareastamp.Data.ChildListExpressStampViewHolder;
import com.ringdingdong.serviceareastamp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016-08-24.
 */
public class CustomExpandableListViewExpressStampAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<String> mParentList;
    private ArrayList<ChildListExpressStampItem> mChildList;
    private ChildListExpressStampViewHolder mChildListViewHolder;
    private HashMap<String, ArrayList<ChildListExpressStampItem>> mChildHashMap;

    public CustomExpandableListViewExpressStampAdapter(Context context, ArrayList<String> parentList, HashMap<String, ArrayList<ChildListExpressStampItem>> childHashMap){
        this.mContext = context;
        this.mParentList = parentList;
        this.mChildHashMap = childHashMap;
    }

    @Override
    public int getGroupCount() {
        return mParentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mParentList.get(groupPosition);
    }

    @Override
    public ChildListExpressStampItem getChild(int groupPosition, int childPosition) {
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater groupInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = groupInfla.inflate(R.layout.parent_listview_express_stamp, parent, false);
        }

        TextView parentText = (TextView)convertView.findViewById(R.id.textView_parent_listview_express_stamp_name);
        parentText.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater childInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInfla.inflate(R.layout.child_listview_express_stamp, null);

            mChildListViewHolder = new ChildListExpressStampViewHolder();
            mChildListViewHolder.mTextViewExpressStampName = (TextView)convertView.findViewById(R.id.textView_my_page_child_listview_express_service_area_name);
            mChildListViewHolder.mButtonExpressStampCheck = (CheckBox) convertView.findViewById(R.id.checkbox_my_page_child_listview_express_stamp_check);
            convertView.setTag(mChildListViewHolder);
        } else{
            mChildListViewHolder = (ChildListExpressStampViewHolder)convertView.getTag();
        }

        mChildListViewHolder.mTextViewExpressStampName.setText(getChild(groupPosition, childPosition).getServiceAreaName());
        mChildListViewHolder.mButtonExpressStampCheck.setChecked(getChild(groupPosition, childPosition).isServiceAreaCheck());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
