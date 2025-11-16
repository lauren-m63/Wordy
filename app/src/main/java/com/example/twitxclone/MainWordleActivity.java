package com.example.twitxclone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainWordleActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;


    // ROW 1
    EditText button11;
    EditText button12;
    EditText button13;
    EditText button14;
    EditText button15;

    // ROW 2
    EditText button21;
    EditText button22;
    EditText button23;
    EditText button24;
    EditText button25;

    // ROW 3
    EditText button31;
    EditText button32;
    EditText button33;
    EditText button34;
    EditText button35;

    // ROW 4
    EditText button41;
    EditText button42;
    EditText button43;
    EditText button44;
    EditText button45;

    // ROW 5
    EditText button51;
    EditText button52;
    EditText button53;
    EditText button54;
    EditText button55;


    // ROW 6
    EditText button61;
    EditText button62;
    EditText button63;
    EditText button64;
    EditText button65;

    Button addWord;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_wordle_activity);

        addWord = findViewById(R.id.AddWordButton);
        // ROW 1
        button11 = findViewById(R.id.button1_1);
        button12 = findViewById(R.id.button1_2);
        button13 = findViewById(R.id.button1_3);
        button14 = findViewById(R.id.button1_4);
        button15 = findViewById(R.id.button1_5);

// ROW 2
        button21 = findViewById(R.id.button2_1);
        button22 = findViewById(R.id.button2_2);
        button23 = findViewById(R.id.button2_3);
        button24 = findViewById(R.id.button2_4);
        button25 = findViewById(R.id.button2_5);

// ROW 3
        button31 = findViewById(R.id.button3_1);
        button32 = findViewById(R.id.button3_2);
        button33 = findViewById(R.id.button3_3);
        button34 = findViewById(R.id.button3_4);
        button35 = findViewById(R.id.button3_5);

// ROW 4
        button41 = findViewById(R.id.button4_1);
        button42 = findViewById(R.id.button4_2);
        button43 = findViewById(R.id.button4_3);
        button44 = findViewById(R.id.button4_4);
        button45 = findViewById(R.id.button4_5);

// ROW 5
        button51 = findViewById(R.id.button5_1);
        button52 = findViewById(R.id.button5_2);
        button53 = findViewById(R.id.button5_3);
        button54 = findViewById(R.id.button5_4);
        button55 = findViewById(R.id.button5_5);

// ROW 6
        button61 = findViewById(R.id.button6_1);
        button62 = findViewById(R.id.button6_2);
        button63 = findViewById(R.id.button6_3);
        button64 = findViewById(R.id.button6_4);
        button65 = findViewById(R.id.button6_5);

    }
}
