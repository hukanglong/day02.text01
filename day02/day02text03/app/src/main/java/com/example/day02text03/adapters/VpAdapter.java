package com.example.day02text03.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.day02text03.R;
import com.example.day02text03.beas.FuliBean;

import java.util.List;

public class VpAdapter extends PagerAdapter {

    private Context mContext;
    private List<FuliBean.ResultsBean> mList;
    private ImageView mImg;
    private TextView mNum;

    public VpAdapter(Context context, List<FuliBean.ResultsBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_imgs, null);
        mImg = view.findViewById(R.id.img);
        mNum = view.findViewById(R.id.num);
        Glide.with(mContext).load(mList.get(position).getUrl()).into(mImg);
        mNum.setText(position+1+" / "+mList.size());
        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
