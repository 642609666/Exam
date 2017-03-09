package com.liyan.android.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liyan.android.R;
import com.liyan.android.bean.HomeBean;
import com.liyan.android.utils.Constants;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/9 0009.
 * 功能:
 */
public class RecommendAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultBean.RecommendInfoBean> datas;

    public RecommendAdapter(Context context, List<HomeBean.ResultBean.RecommendInfoBean> data) {
        this.mContext = context;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.recommend_adapter, null);
            viewHolder = new ViewHolder(convertView);

            //设置tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.tvName.setText(datas.get(position).getName());
        viewHolder.tvPrice.setText(datas.get(position).getCover_price());

        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE + datas.get(position).getFigure())
                .into(viewHolder.ivImage);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_image)
        ImageView ivImage;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
