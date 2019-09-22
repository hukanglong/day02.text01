package com.example.day02text02;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day02text02.adapters.MainAdapter;
import com.example.day02text02.beans.Bean;
import com.example.day02text02.beans.UserBeanDao;
import com.example.day02text02.mvp.presenter.MainPresenter;
import com.example.day02text02.mvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rec)
    RecyclerView mRec;
    private List<Bean.DatasBean> mList = new ArrayList<>();
    private MainAdapter mMainAdapter;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getData();
        initToolbar();
        initRec();
    }

    private void initRec() {
        mRec.setLayoutManager(new LinearLayoutManager(this));
        mMainAdapter = new MainAdapter(this, mList,mMainPresenter);
        mRec.setAdapter(mMainAdapter);

    }

    private void initToolbar() {
        mToolbar.setTitle("列表");
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onSuccess(List<Bean.DatasBean> list) {
        mList.addAll(list);
        mMainAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFile(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
