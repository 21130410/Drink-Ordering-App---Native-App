package com.example.apptest1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptest1.DetailActivity;
import com.example.apptest1.R;
import com.example.apptest1.ViewAllActivity;
import com.example.apptest1.model.RecomendedModel;

import java.io.Serializable;
import java.util.List;

public class RecomendedAdapter extends RecyclerView.Adapter<RecomendedAdapter.ViewHolder> {

    Context context;
    List<RecomendedModel> recomendedModelList;

    public RecomendedAdapter() {
    }

    public RecomendedAdapter(Context context, List<RecomendedModel> recomendedModelList) {
        this.context = context;
        this.recomendedModelList = recomendedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(recomendedModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(recomendedModelList.get(position).getName());
        holder.desciption.setText(recomendedModelList.get(position).getDescription());
        holder.rating.setText(recomendedModelList.get(position).getRating());
        holder.price.setText(recomendedModelList.get(position).getPrice()+"đ");

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", recomendedModelList.get(position));
                context.startActivity(intent);
            }
        });

//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("detail",list.get(position));
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return recomendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
ImageView imageView;
TextView name,desciption,rating,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rec_img);
            name = itemView.findViewById(R.id.rec_name);
            desciption = itemView.findViewById(R.id.rec_dec);
            rating = itemView.findViewById(R.id.rec_rating);
            price = itemView.findViewById(R.id.rec_rice);
        }
    }
   }
