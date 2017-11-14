package com.six.jdcom.adpater;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.six.jdcom.R;
import com.six.jdcom.home.bean.HomeBean;

import java.util.List;

/**
 * Created by lenovo on 2017/10/24.
 */

public class Home_fourAdpater extends RecyclerView.Adapter<MyViewHolder2> {
    List<HomeBean.DataBean.SubjectsBean.GoodsListBeanX> goods;
    Context mcontext2;

    public Home_fourAdpater(List<HomeBean.DataBean.SubjectsBean.GoodsListBeanX> goods, Context mcontext2) {
        this.goods = goods;
        this.mcontext2 = mcontext2;
    }

    //条目点击
    private AdapterView.OnItemClickListener mOnItemClickLitener;



    public void setOnItemClickLitener(AdapterView.OnItemClickListener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {

    MyViewHolder2    holder2=null;

        holder2 = new MyViewHolder2(LayoutInflater.from(
                mcontext2).inflate(R.layout.item_home2, parent,
                false));
        return holder2;
    }

    @Override
    public void onBindViewHolder(MyViewHolder2 holder, int position) {
        // holder.tv.setText(ad5.get(position).getTitle());
        //ImageLoader imageLoader=ImageLoader.getInstance();
        holder.textView1.setText(goods.get(position).getGoods_name());
        //得到图片的url
        Uri uri= Uri.parse(goods.get(position).getGoods_img());
        holder.draweeView1.setImageURI(uri);//设置给Fresco
        // 如果设置了回调，则设置点击事件
       /* if (mOnItemClickLitener != null)
        {
            holder2.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder2.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder2.itemView, pos);

                }
            });

        }*/
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }
}

class MyViewHolder2 extends RecyclerView.ViewHolder
{
    TextView textView1;
    SimpleDraweeView draweeView1;

    public MyViewHolder2(View view)
    {
        super(view);
        textView1 = (TextView) view.findViewById(R.id.item_home2_text);
        //imageView=(ImageView) view.findViewById(R.id.home_img);
        draweeView1=(SimpleDraweeView) view.findViewById(R.id.item_home2_img);
    }
}
