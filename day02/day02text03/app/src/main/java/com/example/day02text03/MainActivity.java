package com.example.day02text03;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.day02text03.adapters.FuliAdapter;
import com.example.day02text03.adapters.VpAdapter;
import com.example.day02text03.beas.FuliBean;
import com.example.day02text03.mvp.pre.FuliPre;
import com.example.day02text03.mvp.view.FuliView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FuliView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.vp)
    ViewPager mVp;
    private List<FuliBean.ResultsBean> mList = new ArrayList<>();
    private FuliAdapter mFuliAdapter;
    private VpAdapter mVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FuliPre fuliPre = new FuliPre(this);
        fuliPre.getData();
        initToolbar();
        initRec();
    }

    private void initToolbar() {
        mToolbar.setTitle("福利");
        setSupportActionBar(mToolbar);
    }

    private void initRec() {
        mRec.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        mFuliAdapter = new FuliAdapter(this, mList);
        mRec.setAdapter(mFuliAdapter);

        mFuliAdapter.setMyOnclick(new FuliAdapter.MyOnclick() {
            @Override
            public void onclick(int position) {
                mVp.setVisibility(View.VISIBLE);
                mVpAdapter = new VpAdapter(MainActivity.this, mList);
                mVp.setAdapter(mVpAdapter);
                mVp.setCurrentItem(position);
            }
        });
    }

    @Override
    public void onSuccess(List<FuliBean.ResultsBean> list) {
        mList.addAll(list);
        mFuliAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFile(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
