package com.jesusrojo.composedemo.listmenuactivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

public abstract class BaseListActivity extends ListActivity {

    private Activity mActivity;
    private SparseArray<Class> mSparseArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        setTitle(mActivity.getClass().getSimpleName());

        mSparseArray = initNewSparseArray();
        if(mSparseArray!=null) {
            setListAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    getArrNames(mSparseArray)));
        }

        ListView listView = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {

                if(mSparseArray!=null) {
                    startActivity(new Intent(mActivity, mSparseArray.get(position)));
                }
            }
        });
    }

    protected abstract SparseArray<Class> initNewSparseArray();

    @NotNull private String[] getArrNames(SparseArray<Class> sparseArray) {
        final int size = sparseArray.size();
        String[] names = new String[size];
        for(int i = 0; i < size; i++) {
            Class obj = sparseArray.get(i);
            if(obj!=null) {
                String name = obj.getSimpleName();
                names[i] = name;
                //noinspection ConstantConditions
                if (name != null) names[i] = name;
            }
        }
        return names;
    }
}