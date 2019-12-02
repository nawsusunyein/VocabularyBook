package com.example.vocabularybook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Write;
import com.google.type.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteVocabActivity extends AppCompatActivity {

    private static final String CN_KEY = "Chinese";
    private static final String EN_KEY = "English";
    private static final String MM_KEY = "Myanmar";

    private static ArrayList<VocabType> mArrayList = new ArrayList<>();

    private String cnWord,enWord,mmWord,id;

    EditText txtChineseWord;
    EditText txtEnglishWord;
    EditText txtMyanmarWord;

    Button btnSave;
    Button btnCancel;

    Boolean isUpdate = false;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_vocab);

        db = FirebaseFirestore.getInstance();
        btnSave = (Button) findViewById(R.id.btnSaveWord);
        btnCancel = (Button) findViewById(R.id.btnCancelWord);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isUpdate){
                    updateVocabulary();
                }else{
                    addNewVocabulary();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDataInTextField();
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            Intent updateIntent = getIntent();
            cnWord = updateIntent.getExtras().getString("cnWord");
            enWord = updateIntent.getExtras().getString("enWord");
            mmWord = updateIntent.getExtras().getString("mmWord");
            id = updateIntent.getExtras().getString("id");
            isUpdate = true;
            setTextfieldValue();
        }else{
            isUpdate = false;
        }


    }

    private void findTextfieldsById(){
        txtChineseWord = (EditText) findViewById(R.id.txtChinese);
        txtEnglishWord = (EditText) findViewById(R.id.txtEnglish);
        txtMyanmarWord = (EditText) findViewById(R.id.txtBurmese);
    }

    private  void setTextfieldValue(){
        findTextfieldsById();
        txtChineseWord.setText(cnWord);
        txtEnglishWord.setText(enWord);
        txtMyanmarWord.setText(mmWord);

    }

    private void addNewVocabulary(){

        findTextfieldsById();
        String cnWord = txtChineseWord.getText().toString();
        String enWord = txtEnglishWord.getText().toString();
        String mmWord = txtMyanmarWord.getText().toString();

        Map<String,Object> newVocabWord = new HashMap<>();
        newVocabWord.put(CN_KEY,cnWord);
        newVocabWord.put(EN_KEY,enWord);
        newVocabWord.put(MM_KEY,mmWord);
        db.collection("VocabularyShu").document().set(newVocabWord)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(WriteVocabActivity.this,"Insert vocab successfully",Toast.LENGTH_LONG).show();
                        clearDataInTextField();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(WriteVocabActivity.this,"Error is " + e.toString() , Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void updateVocabulary(){
        String chineseWord = txtChineseWord.getText().toString();
        String englishWord = txtEnglishWord.getText().toString();
        String myanmarWord = txtMyanmarWord.getText().toString();

        DocumentReference documentReference = db.collection("VocabularyShu").document(id);
        documentReference.update("Chinese",chineseWord);
        documentReference.update("English",englishWord);
        documentReference.update("Myanmar",myanmarWord)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(WriteVocabActivity.this,"Updated successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(WriteVocabActivity.this,"Updated Fails",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void clearDataInTextField(){
        findTextfieldsById();
        txtChineseWord.setText("");
        txtEnglishWord.setText("");
        txtMyanmarWord.setText("");
    }
}
