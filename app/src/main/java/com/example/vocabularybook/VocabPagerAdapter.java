package com.example.vocabularybook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VocabPagerAdapter extends RecyclerView.Adapter<VocabViewHolder> {

    private ArrayList<VocabType> vocabList;
    Context context;
    FirebaseFirestore db;

    public VocabPagerAdapter(ArrayList<VocabType> vocabList,Context context){
        this.vocabList = vocabList;
        this.context = context;
    }

    @NonNull
    @Override
    public VocabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View vocabView = inflater.inflate(R.layout.item_vocab,parent,false);
        VocabViewHolder vocabViewHolder = new VocabViewHolder(vocabView);
        return vocabViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VocabViewHolder holder, final int position) {
        holder.txtCnValue.setText(vocabList.get(position).getChinese());
        holder.txtEnValue.setText(vocabList.get(position).getEnglish());
        holder.txtMmValue.setText(vocabList.get(position).getMyanmar());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent vocabDetailsIntent = new Intent(context,WriteVocabActivity.class);
                vocabDetailsIntent.putExtra("cnWord",vocabList.get(pos).getChinese());
                vocabDetailsIntent.putExtra("enWord",vocabList.get(pos).getEnglish());
                vocabDetailsIntent.putExtra("mmWord",vocabList.get(pos).getMyanmar());
                vocabDetailsIntent.putExtra("id",vocabList.get(pos).getId());
                context.startActivity(vocabDetailsIntent);
            }

            @Override
            public void onItemLongClick(View v, int pos) {
                db = FirebaseFirestore.getInstance();
                db.collection("VocabularyShu").document(vocabList.get(pos).getId()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                vocabList.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                                Toast.makeText(context,"Deleted successfully",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context,"Deletion fail",Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        return vocabList.size();
    }
}
