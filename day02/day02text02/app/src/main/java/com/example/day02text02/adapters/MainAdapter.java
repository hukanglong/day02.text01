package com.example.day02text02.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day02text02.R;
import com.example.day02text02.beans.Bean;
import com.example.day02text02.beans.UserBean;
import com.example.day02text02.mvp.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    private Context mContext;
    private List<Bean.DatasBean> mList;
    private MainPresenter mMainPresenter;

    public MainAdapter(Context context, List<Bean.DatasBean> list, MainPresenter mainPresenter) {
        mContext = context;
        mList = list;
        mMainPresenter = mainPresenter;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getAvatar())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher).circleCrop())
                .into(holder.mImgIt);
        holder.mTitleIt.setText(mList.get(position).getTitle());
        holder.mMsgIt.setText(mList.get(position).getExcerpt());

        List<UserBean> msg = mMainPresenter.getMsg();
        for (int i = 0; i < msg.size(); i++) {
            if(msg.get(i).getTitle().equals(mList.get(position).getTitle())){
                holder.mBtnIt.setText("取消");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.mBtnIt.setBackground(mContext.getDrawable(R.drawable.shape2));
                    }
                }
            }
        }

        holder.mBtnIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mBtnIt.getText().equals("关注")){
                    Bean.DatasBean datasBean = mList.get(position);
                    String avatar = datasBean.getAvatar();
                    String title = datasBean.getTitle();
                    String excerpt = datasBean.getExcerpt();
                    mMainPresenter.addMsg(avatar,title,excerpt);
                    holder.mBtnIt.setText("取消");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.mBtnIt.setBackground(mContext.getDrawable(R.drawable.shape2));
                        }
                    }
                    Toast.makeText(mContext, "关注成功", Toast.LENGTH_SHORT).show();
                }else {
                    List<UserBean> msg = mMainPresenter.getMsg();
                    for (int i = 0; i < msg.size(); i++) {
                        if(msg.get(i).getTitle().equals(mList.get(position).getTitle())){
                            Long id = msg.get(i).getId();
                            mMainPresenter.delete(id);
                        }
                    }
                    holder.mBtnIt.setText("关注");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.mBtnIt.setBackground(mContext.getDrawable(R.drawable.shape));
                        }
                    }
                    Toast.makeText(mContext, "取消关注", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_it)
        ImageView mImgIt;
        @BindView(R.id.title_it)
        TextView mTitleIt;
        @BindView(R.id.msg_it)
        TextView mMsgIt;
        @BindView(R.id.btn_it)
        Button mBtnIt;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
