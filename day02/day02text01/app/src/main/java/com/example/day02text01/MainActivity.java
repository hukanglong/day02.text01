package com.example.day02text01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day02text01.adapters.MainAdapter;
import com.example.day02text01.beans.Bean;
import com.example.day02text01.beans.MsgBeans;
import com.example.day02text01.mvp.pre.MainPre;
import com.example.day02text01.mvp.view.MainView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rec)
    RecyclerView mRec;
    private List<Bean.BodyBean.ResultBean> mList = new ArrayList<>();
    private MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPre mainPre = new MainPre(this);
        mainPre.getData();
        ButterKnife.bind(this);
        initToolbar();
        initRec();
    }

    private void initRec() {
        mRec.setLayoutManager(new LinearLayoutManager(this));
        mMainAdapter = new MainAdapter(this, mList);
        mRec.setAdapter(mMainAdapter);

        mMainAdapter.setMyOnclick(new MainAdapter.MyOnclick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, PartActivity.class);
                startActivity(intent);
                int id = mList.get(position).getID();
                String img = mList.get(position).getTeacherPic();
                String name = mList.get(position).getTeacherName();
                String title = mList.get(position).getTitle();
                EventBus.getDefault().postSticky(new MsgBeans(id,img,name,title));
            }
        });
    }

    private void initToolbar() {
        mToolbar.setTitle("名师推荐");
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onSuccess(List<Bean.BodyBean.ResultBean> list) {
        mList.addAll(list);
        mMainAdapter.notifyDataSetChanged();
        Log.i("tag", "onSuccess: "+mList.size());
    }

    @Override
    public void onFile(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
