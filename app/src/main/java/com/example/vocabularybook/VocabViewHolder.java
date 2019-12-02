package com.example.vocabularybook;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class VocabViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    TextView txtEnValue;
    TextView txtCnValue;
    TextView txtMmValue;
    private ItemClickListener itemClickListener;

    VocabViewHolder(View itemView){
        super(itemView);
        txtCnValue = (TextView) itemView.findViewById(R.id.txtCnValue);
        txtEnValue = (TextView) itemView.findViewById(R.id.txtEnValue);
        txtMmValue = (TextView) itemView.findViewById(R.id.txtMmValue);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }

    @Override
    public boolean onLongClick(View view) {
        this.itemClickListener.onItemLongClick(view,getLayoutPosition());
        return true;
    }
}
