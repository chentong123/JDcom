package com.six.jdcom.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.six.jdcom.R;
import com.six.jdcom.home.ListActivity;
import com.six.jdcom.home.Shop1;
import com.six.jdcom.home.XiangqingActivty;
import com.six.jdcom.home.bean.RightBean;
import com.six.jdcom.home.bean.Shop;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by admin on 2017/11/14.
 */

public class GridAdapter extends BaseAdapter{
    List<RightBean.DataBean.ListBean> data;
    Context context;
    public interface Itemclick{
        void onItimclicks(int position,View view);
    }
    private Itemclick itemclick;

    public void setItemclick(Itemclick itemclick) {
        this.itemclick = itemclick;
    }

    public GridAdapter(List<RightBean.DataBean.ListBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.type_grid_item,null);
            holder.button= (Button) convertView.findViewById(R.id.tv_gv_type);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.button.setText(data.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ListActivity.class);

               EventBus.getDefault().postSticky(new Shop1(data.get(position).getPscid()+""));
                context.startActivity(intent);
            }
        });
        if (itemclick!=null){
            final ViewHolder finalHolder = holder;
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemclick.onItimclicks(position, finalHolder.button);
                }
            });
        }
        return convertView;
    }
    class ViewHolder{
        private Button button;
    }
}
