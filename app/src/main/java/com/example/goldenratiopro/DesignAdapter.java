package com.example.goldenratiopro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.mViewHolder> {

    private List<ExampleModel> exampleModel;

    public DesignAdapter(List<ExampleModel> exampleModel) {

        this.exampleModel = exampleModel;

    }



    @NonNull
    @Override
    public DesignAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.logocard, parent, false);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return new DesignAdapter.mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DesignAdapter.mViewHolder holder, int position) {
        Integer image=exampleModel.get(position).getImage();
        holder.setImageView(image);
    }

    @Override
    public int getItemCount() {
        return exampleModel.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private View view;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }
        public void setImageView(Integer imageview){
            imageView=view.findViewById(R.id.logoImage);
            imageView.setImageResource(imageview);
        }
    }
}
