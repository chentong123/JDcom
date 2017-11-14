package com.six.jdcom.adpater;

import android.content.Context;
import android.content.Intent;
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
import com.six.jdcom.activity.SecondActivity;
import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.home.bean.User;
import com.six.jdcom.utils.OnItemClickLitener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lenovo on 2017/10/23.
 */

public class Home_TwoAdapter extends RecyclerView.Adapter<MyViewHolder> {
     Context context;
    List<HomeBean.DataBean.Ad5Bean> ad5;

    public Home_TwoAdapter(List<HomeBean.DataBean.Ad5Bean> ad5, Context context) {
        this.ad5 = ad5;
        this.context = context;
    }

    //条目点击
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder=null;
        holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false));
        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position){
        holder.tv.setText(ad5.get(position).getTitle());
        //ImageLoader imageLoader=ImageLoader.getInstance();
        //得到图片的url
        Uri uri= Uri.parse(ad5.get(position).getImage());
        holder.draweeView.setImageURI(uri);//设置给Fresco
        holder.draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String s=ad5.get(position).getUrl();
                EventBus.getDefault().postSticky(new User(s));
                Intent intent=new Intent(context,SecondActivity.class);
                context.startActivity(intent);
            }
        });
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);

                }
            });
        }
    }
    @Override
    public int getItemCount() {

        return ad5.size();
    }

}

class MyViewHolder extends RecyclerView.ViewHolder
{
    TextView tv;
    SimpleDraweeView draweeView;
    // public ImageView imageView1;

    public MyViewHolder(View view)
    {
        super(view);
        tv = (TextView) view.findViewById(R.id.home_text);
        draweeView=(SimpleDraweeView) view.findViewById(R.id.img);
        //imageView1=(ImageView) view.findViewById(R.id.home1_img);
    }
}
