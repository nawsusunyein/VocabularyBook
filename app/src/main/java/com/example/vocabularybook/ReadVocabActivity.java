package com.example.vocabularybook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ReadVocabActivity extends AppCompatActivity {

    FirebaseFirestore db;
    private static ArrayList<VocabType> mArrayList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_vocab);
        db = FirebaseFirestore.getInstance();
        readVocabularyList();

    }

    private void readVocabularyList(){
        mArrayList.clear();
        CollectionReference notesRef = db.collection("VocabularyShu");
        notesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String cnWord = document.getString("Chinese");
                        String enWord = document.getString("English");
                        String mmWord = document.getString("Myanmar");
                        String id = document.getId();
                        VocabType vocabContent = new VocabType(cnWord,enWord,mmWord,id);
                        mArrayList.add(vocabContent);
                    }
                    recyclerView = (RecyclerView) findViewById(R.id.recyclerVocabList);
                    VocabPagerAdapter vocabPagerAdapter = new VocabPagerAdapter(mArrayList,getApplicationContext());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadVocabActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(vocabPagerAdapter);
                }
            }
        });
      /*  recyclerView = (RecyclerView) findViewById(R.id.recyclerVocabList);
        VocabPagerAdapter vocabPagerAdapter = new VocabPagerAdapter(mArrayList,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(vocabPagerAdapter);*/
    }
}
