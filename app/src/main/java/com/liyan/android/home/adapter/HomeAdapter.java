package com.liyan.android.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.liyan.android.R;
import com.liyan.android.bean.HomeBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ${
 * 李岩
 * QQ/微信: 642609666} on 3/9 0009.
 * 功能:
 */
public class HomeAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    private final HomeBean.ResultBean mResult;
    private final LayoutInflater inflater;

    private static final int RECOMMEND = 0;
    private static final int HOT = 1;



    private int tempType;

    public HomeAdapter(Context context, HomeBean.ResultBean result) {
        this.mContext = context;
        this.mResult = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    /**
     * 获取项目视图类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (RECOMMEND == position) {
            tempType = RECOMMEND;
        } else if (HOT == position) {
            tempType = HOT;
        }

        return tempType;
    }

    /**
     * 创建视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (RECOMMEND == viewType) {
            View view = inflater.inflate(R.layout.recommend_item_adapter, null);
            return new RecommendViewHolder(mContext, view);
        } else if (HOT == viewType) {
            View view = inflater.inflate(R.layout.hot_item_adapter, null);
            return new HotViewHolder(mContext, view);
        }
        return null;
    }

    /**
     * 绑定视图
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(mResult.getRecommend_info());
        } else if (position == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(mResult.getHot_info());
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.gv_adapter)
        GridView gvAdapter;
        private RecommendAdapter mAdapter;

        public RecommendViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);
        }


        public void setData(List<HomeBean.ResultBean.RecommendInfoBean> data) {

            mAdapter = new RecommendAdapter(context, data);

            gvAdapter.setAdapter(mAdapter);
            //设置点击事件
            gvAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "考试好无聊 + " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

     class HotViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private HotAdapter mAdapter;
        @InjectView(R.id.gv_adapter_hot)
        GridView gvAdapterHot;

        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<HomeBean.ResultBean.HotInfoBean> data) {
            mAdapter = new HotAdapter(context, data);

            gvAdapterHot.setAdapter(mAdapter);

            gvAdapterHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "考试好无聊 + " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
