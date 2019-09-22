package com.example.day02text01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day02text01.R;
import com.example.day02text01.beans.Bean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    private Context mContext;
    private List<Bean.BodyBean.ResultBean> mList;

    public MainAdapter(Context context, List<Bean.BodyBean.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.mAuthorIt.setText(mList.get(position).getTeacherName());
        Glide.with(mContext).load(mList.get(position).getTeacherPic())
                .apply(new RequestOptions().circleCrop().placeholder(R.mipmap.ic_launcher))
                .into(holder.mImgIt);
        holder.mTitleIt.setText(mList.get(position).getTitle());
        holder.mMsgIt.setText("#通用管理# #家庭教育#");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOnclick.onClick(position);
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
        @BindView(R.id.btn_it)
        Button mBtnIt;
        @BindView(R.id.author_it)
        TextView mAuthorIt;
        @BindView(R.id.title_it)
        TextView mTitleIt;
        @BindView(R.id.msg_it)
        TextView mMsgIt;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private MyOnclick MyOnclick;

    public void setMyOnclick(MainAdapter.MyOnclick myOnclick) {
        MyOnclick = myOnclick;
    }

    public interface MyOnclick{
        void onClick(int position);
    }
}
