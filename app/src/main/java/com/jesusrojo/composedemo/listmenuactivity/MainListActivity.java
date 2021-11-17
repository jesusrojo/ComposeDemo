package com.jesusrojo.composedemo.listmenuactivity;


import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;

import com.jesusrojo.composedemo.basic.BasicMainActivity;
import com.jesusrojo.composedemo.recyclersimple.RecyclerSimpleActivity;
import com.jesusrojo.composedemo.state.examples.HelloActivityCompose;
import com.jesusrojo.composedemo.state.examples.HelloCodeLabActivityWithViewModel;
import com.jesusrojo.composedemo.state.examples.HelloCodelabActivity;
import com.jesusrojo.composedemo.state.examples.start.TodoActivityStart;
import com.jesusrojo.composedemo.state.todo.TodoActivity;
import com.jesusrojo.composedemo.uidata.presentation.ui.UiDataActivity;


public class MainListActivity extends BaseListActivity {

    @SuppressWarnings("unused")
    public static void makeIntent(Activity a) {
        Intent i = new Intent(a, MainListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        a.startActivity(i);
    }

    @SuppressWarnings("rawtypes")
    @Override protected SparseArray<Class> initNewSparseArray(){
        SparseArray<Class> result = new SparseArray<>();
        result.put(0, HelloCodelabActivity.class);
        result.put(1, HelloCodeLabActivityWithViewModel.class);
        result.put(2, HelloActivityCompose.class);
        result.put(3, BasicMainActivity.class);
        result.put(4, TodoActivityStart.class);
        result.put(5, TodoActivity.class);
        result.put(6, UiDataActivity.class);
        result.put(7, RecyclerSimpleActivity.class);

        return result;
    }
}