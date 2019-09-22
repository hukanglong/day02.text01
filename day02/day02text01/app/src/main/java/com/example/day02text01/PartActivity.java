package com.example.day02text01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.day02text01.adapters.FragAdapter;
import com.example.day02text01.beans.MsgBeans;
import com.example.day02text01.beans.PartBean;
import com.example.day02text01.mvp.pre.PartPre;
import com.example.day02text01.mvp.view.PartView;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartActivity extends AppCompatActivity implements PartView {

    @BindView(R.id.img_part)
    ImageView mImgPart;
    @BindView(R.id.btn_part)
    Button mBtnPart;
    @BindView(R.id.author_part)
    TextView mAuthorPart;
    @BindView(R.id.title_part)
    TextView mTitlePart;
    @BindView(R.id.msg_part)
    TextView mMsgPart;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private List<String> tabs = new ArrayList<>();
    private List<String> msg = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private int mId;
    private FragAdapter mFragAdapter;
    private  List<PartBean.BodyBean.ResultBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        PartPre partPre = new PartPre(this);
        partPre.getData(mId);

    }

    private void initView() {
        for (int i = 0; i < mList.size(); i++) {
            tabs.add(mList.get(i).getDescription());
            msg.add(mList.get(i).getTeacherpowerid());
        }
    }

    private void initVp() {
        for (int i = 0; i < mList.size(); i++) {
            BlankFragment blankFragment = new BlankFragment();
            Bundle bundle = new Bundle();
            bundle.putString("msg",msg.get(i));
            blankFragment.setArguments(bundle);
            mFragments.add(blankFragment);
        }
        mFragAdapter = new FragAdapter(getSupportFragmentManager(), this, mFragments);
        mVp.setAdapter(mFragAdapter);
        mTab.setupWithViewPager(mVp);
        for(int i=0;i<tabs.size();i++){
            mTab.getTabAt(i).setText(tabs.get(i));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMsg(MsgBeans msgBeans){
        mId = msgBeans.getId();
        String img = msgBeans.getImg();
        Glide.with(this).load(img).into(mImgPart);
        String title = msgBeans.getTitle();
        mTitlePart.setText(title);
        String name = msgBeans.getName();
        mAuthorPart.setText(name);
        mMsgPart.setText("#通用管理# #家庭教育#");
    }

    @Override
    public void onSuccess( List<PartBean.BodyBean.ResultBean> list) {
        mList.addAll(list);
        initView();
        initVp();
    }

    @Override
    public void onFile(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
