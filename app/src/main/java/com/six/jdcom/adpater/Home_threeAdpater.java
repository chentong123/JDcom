package com.six.jdcom.adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.six.jdcom.R;
import com.six.jdcom.home.ShopActivity;
import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.utils.OnItemClickLitener;

import java.util.List;

/**
 * Created by lenovo on 2017/10/23.
 */

public class Home_threeAdpater  extends RecyclerView.Adapter<MyViewHolder1> {

    List<HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> data1;
            Context mcontext1;

    public Home_threeAdpater(List<HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> data1, Context mcontext1) {
        this.data1 = data1;
        this.mcontext1 = mcontext1;
    }

    //条目点击
    private OnItemClickLitener mOnItemClickLitener;



    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
    MyViewHolder1 holder1=null;
        holder1 = new MyViewHolder1(LayoutInflater.from(
                mcontext1).inflate(R.layout.item_home1, parent,
                false));
        return holder1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder1 holder, int position) {
        // holder.tv.setText(ad5.get(position).getTitle());
        //ImageLoader imageLoader=ImageLoader.getInstance();
        //得到图片的url
        Uri uri= Uri.parse(data1.get(position).getActivityImg());
        holder.imageView1.setImageURI(uri);//设置给Fresco
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mcontext1, ShopActivity.class);
                mcontext1.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }
}

class MyViewHolder1 extends RecyclerView.ViewHolder
{

    SimpleDraweeView imageView1;

    public MyViewHolder1(View view)
    {
        super(view);
        // tv = (TextView) view.findViewById(R.id.home_text);
        //imageView=(ImageView) view.findViewById(R.id.home_img);
        imageView1=(SimpleDraweeView) view.findViewById(R.id.home1_img);
    }
}
