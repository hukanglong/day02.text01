package com.example.day02text03.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day02text03.R;
import com.example.day02text03.beas.FuliBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.MyHolder> {

    private Context mContext;
    private List<FuliBean.ResultsBean> mList;

    public FuliAdapter(Context context, List<FuliBean.ResultsBean> list) {
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
        Glide.with(mContext).load(mList.get(position).getUrl())
                .into(holder.mImgIt);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOnclick.onclick(position);
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
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private MyOnclick MyOnclick;

    public void setMyOnclick(FuliAdapter.MyOnclick myOnclick) {
        MyOnclick = myOnclick;
    }

    public interface MyOnclick{
        void onclick(int position);
    }
}
