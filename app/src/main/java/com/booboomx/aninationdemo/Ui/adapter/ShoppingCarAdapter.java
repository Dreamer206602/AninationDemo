package com.booboomx.aninationdemo.Ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.booboomx.aninationdemo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by booboomx on 17/5/12.
 */

public class ShoppingCarAdapter extends RecyclerView.Adapter<ShoppingCarAdapter.ViewHolder>{

    private Context mContext;
    private List<String>Datas;

    public ShoppingCarAdapter(Context context, List<String> datas) {
        mContext = context;
        Datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shoping_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(Datas.get(position));

        if (mListener != null) {

            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v,position);
                }
            });

        }



    }

    @Override
    public int getItemCount() {
        return Datas.size()>0?Datas.size():0;
    }

    public interface  OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.imageView)
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
