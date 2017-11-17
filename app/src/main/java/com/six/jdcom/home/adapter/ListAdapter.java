package com.six.jdcom.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.six.jdcom.R;
import com.six.jdcom.home.Linear;
import com.six.jdcom.home.Shop1;
import com.six.jdcom.home.Shop2;
import com.six.jdcom.home.XiangqingActivty;
import com.six.jdcom.utils.OnItemClickLitener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by chentong on 2017/11/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder1>{
    Context context;
    List<Linear.DataBean> mdata;
    public ListAdapter(Context context, List<Linear.DataBean> mdata) {
        this.context = context;
        this.mdata = mdata;
    }
    //条目点击
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder1 holder = new MyViewHolder1(LayoutInflater.from(
                context).inflate(R.layout.item_list, parent,
                false));
        return holder;
    }
    public void onBindViewHolder(final MyViewHolder1 holder, final int position)
    {
        //  holder.tv.setText(data.get(position).getTitle());
        holder.tv.setText(mdata.get(position).getTitle());
        String images=mdata.get(position).getImages();
        String[] split = images.split("[|]");
        String[] split1 = split[0].split("[!]");
        Uri uri= Uri.parse(split1[0]);
        holder.draweeView.setImageURI(uri);//设置给Fresco
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
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent=new Intent(context, XiangqingActivty.class);
              //  EventBus.getDefault().postSticky(new Shop2(mdata.get(position).getPscid()+""));
               // context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount()
    {
        return mdata.size();
    }


    class MyViewHolder1 extends RecyclerView.ViewHolder
    {
        TextView tv;
        public SimpleDraweeView draweeView;
        public MyViewHolder1(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.i_text);
            draweeView=(SimpleDraweeView) view.findViewById(R.id.img);

        }
    }
}
