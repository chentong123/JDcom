package com.six.jdcom.home.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.six.jdcom.R;
import com.six.jdcom.cart.MyGridView;
import com.six.jdcom.home.adapter.GridAdapter;
import com.six.jdcom.home.adapter.RightAdapter;
import com.six.jdcom.home.bean.RightBean;

import java.util.List;

/**
 * Created by chentong on 2017/11/21.
 */

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.MyViewHolders> {
    List<AddSelect.DataBean> data;
    Context context;

    public SelectAdapter(List<AddSelect.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public SelectAdapter.MyViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        SelectAdapter.MyViewHolders holder=new SelectAdapter.MyViewHolders(LayoutInflater.from(context)
                .inflate(R.layout.dingdan,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(SelectAdapter.MyViewHolders holder, int position) {
        holder.right.setText(data.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolders extends RecyclerView.ViewHolder{
        private TextView right;

        public MyViewHolders(View itemView) {
            super(itemView);
            right= (TextView) itemView.findViewById(R.id.dian_text);

        }
    }
}

