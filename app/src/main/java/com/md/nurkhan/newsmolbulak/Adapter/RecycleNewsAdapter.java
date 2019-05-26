package com.md.nurkhan.newsmolbulak.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.md.nurkhan.newsmolbulak.Model.NewsModel;
import com.md.nurkhan.newsmolbulak.R;

import java.util.List;


public class RecycleNewsAdapter extends RecyclerView.Adapter<RecycleNewsAdapter.ViewHolder> {

    private List<NewsModel> list;
    private Context mContext;
    private newsClickListener listener;

    public interface newsClickListener {

        void onNewsClick(int pos);
    }
    public RecycleNewsAdapter(List<NewsModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        mContext = context;
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    public void setClickListener(newsClickListener clickListener) {
        this.listener = clickListener;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final NewsModel cafeDate = list.get(position);
        Glide.with(mContext).load(cafeDate.getImage()).into(holder.imageView);
        holder.title.setText(cafeDate.getTitle());
        holder.description.setText(cafeDate.getDescription());
        holder.data.setText(cafeDate.getData());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNewsClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {

            return list.size();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView imageView;
        TextView data;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle);
            description = itemView.findViewById(R.id.txtDescription);
            imageView = itemView.findViewById(R.id.img);
            data = itemView.findViewById(R.id.txtData);
        }
    }
}
