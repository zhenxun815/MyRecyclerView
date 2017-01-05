package com.yiheng.myrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.yiheng.myrecyclerview.adapters.ItemClickAdapter;
import com.yiheng.myrecyclerview.interfaces.OnRecyclerViewItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mList = initData();
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ItemClickAdapter clickAdapter = new ItemClickAdapter(this.mList);
        recyclerview.setAdapter(clickAdapter);
        recyclerview.addOnItemTouchListener(new OnRecyclerViewItemClickListener(recyclerview) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int itemId = (int) vh.itemView.getTag();
                Toast.makeText(recyclerview.getContext(), itemId+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private ArrayList<String> initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add("item" + i);
        }
        return mList;
    }

}
