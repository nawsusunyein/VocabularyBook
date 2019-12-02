package com.example.vocabularybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnWriteVocab;
    Button btnReadVocab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWriteVocab = (Button) findViewById(R.id.btnWriteVocab);
        btnReadVocab = (Button) findViewById(R.id.btnReadVocab);

        btnReadVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readVocabIntent = new Intent(MainActivity.this,ReadVocabActivity.class);
                startActivity(readVocabIntent);
            }
        });

        btnWriteVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeVocabIntent = new Intent(MainActivity.this,WriteVocabActivity.class);
                startActivity(writeVocabIntent);
            }
        });
    }
}
