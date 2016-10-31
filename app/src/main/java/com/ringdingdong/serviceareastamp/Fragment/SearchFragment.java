package com.ringdingdong.serviceareastamp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ringdingdong.serviceareastamp.Activity.FilterResultActivity;
import com.ringdingdong.serviceareastamp.Activity.SearchResultActivity;
import com.ringdingdong.serviceareastamp.R;

/**
 * Created by Administrator on 2016-08-23.
 */
public class SearchFragment extends Fragment {
    EditText editText_search;
    Button button_search_ok, button_search_filtering;
    CheckBox checkbox_search_e, checkbox_search_s, checkbox_search_w, checkbox_search_g, checkbox_search_h, checkbox_search_t;

    String filter = " ";
    int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);

        editText_search = (EditText) v.findViewById(R.id.editText_search);
        button_search_ok = (Button) v.findViewById(R.id.button_search_ok);
        button_search_filtering = (Button) v.findViewById(R.id.button_search_filtering);
        checkbox_search_e = (CheckBox) v.findViewById(R.id.checkbox_search_e);
        checkbox_search_s = (CheckBox) v.findViewById(R.id.checkbox_search_s);
        checkbox_search_w = (CheckBox) v.findViewById(R.id.checkbox_search_w);
        checkbox_search_g = (CheckBox) v.findViewById(R.id.checkbox_search_g);
        checkbox_search_h = (CheckBox) v.findViewById(R.id.checkbox_search_h);
        checkbox_search_t = (CheckBox) v.findViewById(R.id.checkbox_search_t);

        button_search_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("keyword", editText_search.getText().toString());
                startActivity(intent);
            }
        });

        checkbox_search_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox_search_e.isChecked()) {
                    filter = filter + ",수유실";
                    count++;
                }
            }
        });
        checkbox_search_s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox_search_s.isChecked()) {
                    filter = filter + ",수면실";
                    count++;
                }
            }
        });
        checkbox_search_w.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox_search_w.isChecked()) {
                    filter = filter + ",약국";
                    count++;
                }
            }
        });
        checkbox_search_g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox_search_g.isChecked()) {
                    filter = filter + ",샤워실";
                    count++;
                }
            }
        });
        checkbox_search_h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox_search_h.isChecked()) {
                    filter = filter + ",세탁실";
                    count++;
                }
            }
        });
        checkbox_search_t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkbox_search_t.isChecked()) {
                    filter = filter + ",내고장특산물";
                    count++;
                }
            }
        });
        button_search_filtering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FilterResultActivity.class);
                intent.putExtra("filter", filter);
                intent.putExtra("count", count);
                startActivity(intent);
            }
        });

        return v;
    }

}
