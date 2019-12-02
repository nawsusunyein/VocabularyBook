package com.example.vocabularybook;

import android.view.View;

public interface ItemClickListener {
    void onItemClick(View v,int pos);
    void onItemLongClick(View v,int pos);
}
