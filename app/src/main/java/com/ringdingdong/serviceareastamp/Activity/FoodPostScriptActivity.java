package com.ringdingdong.serviceareastamp.Activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ringdingdong.serviceareastamp.Adapter.ListViewFoodPostScriptAdapter;
import com.ringdingdong.serviceareastamp.Data.ListViewFoodPostScriptItem;
import com.ringdingdong.serviceareastamp.R;

public class FoodPostScriptActivity extends AppCompatActivity {

    ListView listview ;
    ListViewFoodPostScriptAdapter adapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_food_post_script);

        actionBar = getSupportActionBar();
        actionBar.setTitle("먹거리후기");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        adapter = new ListViewFoodPostScriptAdapter();

        listview = (ListView) findViewById(R.id.listView_food_post_script);
        listview.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_24dp), "너무 맛잇어요", "닉네임", "5","2016/05/24") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_24dp), "너무 별로", "닉네임", "4","2016/05/24") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewFoodPostScriptItem item = (ListViewFoodPostScriptItem) parent.getItemAtPosition(position) ;

                String name = item.getName() ;
                // TODO : use item data.
            }
        }) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
