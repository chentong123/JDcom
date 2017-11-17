package com.six.jdcom.adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.six.jdcom.R;
import com.six.jdcom.activity.BannerActivity;
import com.six.jdcom.activity.SearchActivity;
import com.six.jdcom.activity.SecondActivity;
import com.six.jdcom.home.ShopActivity;
import com.six.jdcom.home.bean.Ad_type_dynamic_data;
import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.home.bean.User;
import com.six.jdcom.utils.GlideImageLoader;
import com.six.jdcom.utils.OnItemClickLitener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by chentong on 2017/11/9.
 */

public class XRAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    HomeBean.DataBean list;
    Context mcontext;
    ArrayList mlist,mlist1;
    private ArrayList<HomeBean.DataBean.Ad5Bean> ad5;
    List<HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> data1;
    List<HomeBean.DataBean.SubjectsBean.GoodsListBeanX> goods;
    List<HomeBean.DataBean.SubjectsBean.GoodsRelationListBean> goods2;
    private  enum Item_Type{
        Typeone,Typetwo,Typethree,Typefour,Typefive,Typesix
    }

    public XRAdapter(Context mcontext, HomeBean.DataBean list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Item_Type.Typeone.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_a, null);
            ViewHolderA viewHolder = new ViewHolderA(mView);
            return viewHolder;

        } else if (viewType == Item_Type.Typetwo.ordinal()) {

            View mView1 = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_b, null);
            ViewHolderB viewHolder = new ViewHolderB(mView1);
            return viewHolder;
        } else if (viewType == Item_Type.Typethree.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_c, null);
            ViewHolderC viewHolder = new ViewHolderC(mView);
            return viewHolder;
        }else if (viewType == Item_Type.Typefour.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_d, null);
            ViewHolderD viewHolder = new ViewHolderD(mView);
            return viewHolder;
        }else if(viewType==Item_Type.Typefive.ordinal())
        {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_e, null);
            ViewHolderE viewHolder = new ViewHolderE(mView);
            return viewHolder;
        }else if(viewType==Item_Type.Typesix.ordinal())
        {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_f, null);
            ViewHolderF viewHolder = new ViewHolderF(mView);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderA) {
            mlist=new ArrayList();
            for(int i=0;i<list.getAd1().size();i++){
                mlist.add(list.getAd1().get(i).getImage());
            }
            //设置图片加载器
            ((ViewHolderA) holder).mbanner.setImageLoader(new GlideImageLoader());
            ((ViewHolderA) holder).mbanner.setImages(mlist);
            ((ViewHolderA) holder).mbanner.start();
            ((ViewHolderA) holder).mbanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    String ad_type_dynamic_data1 = list.getAd1().get(0).getAd_type_dynamic_data();
                    /*for(int j=0;j<list.getAd1().size();j++){
                        String ad_type_dynamic_data = list.getAd1().get(j).getAd_type_dynamic_data();
                    }*/
                    EventBus.getDefault().postSticky(new Ad_type_dynamic_data(ad_type_dynamic_data1));
                    Intent intent=new Intent(mcontext,BannerActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        } else if (holder instanceof ViewHolderB) {

            final List<HomeBean.DataBean.Ad5Bean> ad5 = list.getAd5();
            final ViewHolderB holderB= (ViewHolderB) holder;
            holderB.recyclerView.setLayoutManager(new GridLayoutManager(mcontext,ad5.size()));
            Home_TwoAdapter adapter = new Home_TwoAdapter(ad5, mcontext);
            holderB.recyclerView.setAdapter(adapter);
          /* adapter.setOnItemClickLitener(new OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    holderB
                    EventBus.getDefault().postSticky(new User());
                    finish();
                    Intent intent=new Intent(,SecondActivity.class);
                    startActivity(intent);
                }
                @Override
                public void onItemLongClick(View view, int position) {
                }
            });
*/
        } else if (holder instanceof ViewHolderC) {
            List<HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> data1 = list.getActivityInfo().getActivityInfoList();
            ViewHolderC holderC= (ViewHolderC) holder;
            holderC.countdownView.start(100000000);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holderC.recyclerView1.setLayoutManager(linearLayoutManager);
            Home_threeAdpater adapter1 = new Home_threeAdpater(data1,mcontext);
            holderC.recyclerView1.setAdapter(adapter1);
            adapter1.setOnItemClickLitener(new OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent=new Intent(mcontext, ShopActivity.class);
                    mcontext.startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
        }else if (holder instanceof ViewHolderD) {
            String s=list.getSubjects().get(2).getDescImage();
            ViewHolderD holderD= (ViewHolderD) holder;
            Uri uri= Uri.parse(list.getSubjects().get(2).getDescImage());
            holderD.d_img.setImageURI(uri);//设置给Fresco
            holderD.d_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mcontext, ShopActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        }else if (holder instanceof ViewHolderE)
        {
            goods = list.getSubjects().get(2).getGoodsList();
            ViewHolderE holderE= (ViewHolderE) holder;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holderE.recy2.setLayoutManager(linearLayoutManager);
            Home_fourAdpater adapter2 = new Home_fourAdpater(goods,mcontext);
            holderE.recy2.setAdapter(adapter2);
            adapter2.setOnItemClickLitener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(mcontext, ShopActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        }else if(holder instanceof ViewHolderF)
        {
            goods2=list.getSubjects().get(3).getGoodsRelationList();
            ViewHolderF holderF= (ViewHolderF) holder;
            holderF.recy3.setLayoutManager(new GridLayoutManager(mcontext,2));
            Home_fiveAdpater adapter3 = new Home_fiveAdpater(goods2,mcontext);
            holderF.recy3.setAdapter(adapter3);
            adapter3.setOnItemClickLitener(new OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent=new Intent(mcontext, ShopActivity.class);
                    mcontext.startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return Item_Type.Typeone.ordinal();
        } else if (position == 1) {
            return Item_Type.Typetwo.ordinal();
        } else if (position == 2) {
            return Item_Type.Typethree.ordinal();
        }else if (position == 3) {
            return Item_Type.Typefour.ordinal();
        }else if(position==4)
        {
            return Item_Type.Typefive.ordinal();
        }else if(position==5)
        {
            return Item_Type.Typesix.ordinal();
        }
        return -1;
    }
    class ViewHolderA extends RecyclerView.ViewHolder {
        public Banner mbanner;

        public ViewHolderA(View itemView) {
            super(itemView);

            mbanner = (Banner) itemView.findViewById(R.id.mybanner);
           mbanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mcontext, SearchActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        }
    }


    class ViewHolderB extends RecyclerView.ViewHolder {


        RecyclerView recyclerView;

        public ViewHolderB(View itemView) {
            super(itemView);
            recyclerView=(RecyclerView) itemView.findViewById(R.id.recy);

        }
    }

    class ViewHolderC extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView1;
       CountdownView countdownView;
        public ViewHolderC(View itemView) {
            super(itemView);
            recyclerView1=(RecyclerView) itemView.findViewById(R.id.recy1);
            countdownView=(CountdownView) itemView.findViewById(R.id.countdownView);
        }
    }
    class ViewHolderD extends RecyclerView.ViewHolder {

        /* public Banner mybanner1;*/
        public SimpleDraweeView d_img;
        public ViewHolderD(View itemView) {
            super(itemView);
           /* mybanner1 = (Banner) itemView.findViewById(R.id.mybanner1);*/
            d_img=(SimpleDraweeView) itemView.findViewById(R.id.d_img);
        }
    }
    class ViewHolderE extends RecyclerView.ViewHolder {
        RecyclerView recy2;
        public ViewHolderE(View itemView) {
            super(itemView);
            recy2=(RecyclerView) itemView.findViewById(R.id.recy2);
        }
    }
    class ViewHolderF extends RecyclerView.ViewHolder {
        RecyclerView recy3;
        public ViewHolderF(View itemView) {
            super(itemView);
            recy3=(RecyclerView) itemView.findViewById(R.id.recy3);
        }
    }
}

