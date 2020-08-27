package com.example.goldenratiopro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.mViewHolder> {

    private List<HomeModel> homeModels;

    private OnNoteListener mOnNoteListener;
    public HomePageAdapter(List<HomeModel> homeModels, OnNoteListener mOnNoteListener) {

        this.homeModels = homeModels;
        this.mOnNoteListener= mOnNoteListener;


    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.examplecard, parent, false);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return new HomePageAdapter.mViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        Integer image=homeModels.get(position).getImage();
        holder.setImageView(image);
    }

    @Override
    public int getItemCount() {
        return homeModels.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private View view;
        OnNoteListener onNoteListener;

        public mViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            view=itemView;
            view.setOnClickListener(this);
            this.onNoteListener=onNoteListener;
        }

        public void setImageView(Integer imageview){
            imageView=view.findViewById(R.id.imageCat);
            imageView.setImageResource(imageview);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.OnNoteClick(getAdapterPosition());
        }


    }
    public interface OnNoteListener{
        void OnNoteClick(int position);
    }


}

