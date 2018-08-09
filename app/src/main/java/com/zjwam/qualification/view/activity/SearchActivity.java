package com.zjwam.qualification.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zjwam.qualification.R;
import com.zjwam.qualification.adapter.SearchTJAdapter;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.SearchTJBean;
import com.zjwam.qualification.presenter.SearchTjPresenter;
import com.zjwam.qualification.presenter.ipresenter.ISearchTjPresenter;
import com.zjwam.qualification.utils.KeyboardUtils;
import com.zjwam.qualification.view.iview.ISearchTJView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements ISearchTJView{

    private LRecyclerView tj_recyclerview,ls_recyclerview;
    private SearchTJAdapter searchTJAdapter,lsSearchTJAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayout search_lsjl;
    private ISearchTjPresenter searchTjPresenter;
    private EditText search_title_text;
    private List<SearchTJBean> li_search;
    private TextView clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
    }

    private void initData() {
        searchTjPresenter = new SearchTjPresenter(this,this);
        searchTJAdapter = new SearchTJAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(searchTJAdapter);
        tj_recyclerview.setAdapter(lRecyclerViewAdapter);
        tj_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        tj_recyclerview.setLoadMoreEnabled(false);
        tj_recyclerview.setPullRefreshEnabled(false);
        searchTjPresenter.getTJ();
        li_search = new ArrayList<>();
        search_title_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    KeyboardUtils.hideKeyboard(search_title_text);
                    error("搜索");
                    SearchTJBean searchTJBean = new SearchTJBean();
                    searchTJBean.setName(search_title_text.getText().toString());
                    li_search.add(searchTJBean);
                    lsSearchTJAdapter = new SearchTJAdapter(getBaseContext());
                    lsSearchTJAdapter.addAll(li_search);
                    lRecyclerViewAdapter = new LRecyclerViewAdapter(lsSearchTJAdapter);
                    ls_recyclerview.setAdapter(lRecyclerViewAdapter);
                    ls_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    ls_recyclerview.setLoadMoreEnabled(false);
                    ls_recyclerview.setPullRefreshEnabled(false);
                    search_lsjl.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lsSearchTJAdapter.clear();
                lsSearchTJAdapter.notifyDataSetChanged();
                search_lsjl.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        tj_recyclerview = findViewById(R.id.tj_recyclerview);
        ls_recyclerview = findViewById(R.id.ls_recyclerview);
        search_lsjl = findViewById(R.id.search_lsjl);
        search_lsjl.setVisibility(View.GONE);
        search_title_text = findViewById(R.id.search_title_text);
        clear = findViewById(R.id.clear);
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

    @Override
    public void setTJ(List<SearchTJBean> list) {
        Log.i("---list",list.toString());
        searchTJAdapter.addAll(list);
    }

    @Override
    public void refreshComplete() {
        tj_recyclerview.refreshComplete(10);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }
}
