package com.six.jdcom.adpater;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.six.jdcom.R;
import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.utils.OnItemClickLitener;

import java.util.List;

/**
 * Created by lenovo on 2017/10/24.
 */

public class Home_fiveAdpater extends RecyclerView.Adapter<Home_fiveAdpater.MyViewHolder3>
{
    List<HomeBean.DataBean.SubjectsBean.GoodsRelationListBean> goods2;
    Context mcontext3;
    MyViewHolder3 holder3=null;

    public Home_fiveAdpater(List<HomeBean.DataBean.SubjectsBean.GoodsRelationListBean> goods2, Context mcontext3) {
        this.goods2 = goods2;
        this.mcontext3 = mcontext3;
    }
    //条目点击
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    @Override
    public MyViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {

        holder3 = new MyViewHolder3(LayoutInflater.from(
                mcontext3).inflate(R.layout.item_home3, parent,
                false));
        return holder3;
    }

    @Override
    public void onBindViewHolder(MyViewHolder3 holder, int position) {
        holder3.tv3.setText(goods2.get(position).getDescription());
        //得到图片的url
        Uri uri= Uri.parse(goods2.get(position).getGoodsImage());
        holder.home3.setImageURI(uri);//设置给Fresco
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder3.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder3.itemView, pos);

                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return goods2.size();
    }

    class MyViewHolder3 extends RecyclerView.ViewHolder {

        TextView tv3;
        SimpleDraweeView home3;
        public MyViewHolder3(View view) {
            super(view);
            tv3 = (TextView) view.findViewById(R.id.home3_text);
            home3=(SimpleDraweeView) view.findViewById(R.id.home3_img);
        }
    }
}
