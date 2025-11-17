package com.example.twitxclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.twitxclone.model.Word;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MainWordleActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    String secretWord;
    ArrayList<String> list;
    int totalPoints= 0;


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

    Button addWordButton;
    Button submitButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_wordle_activity);

        auth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        addWordButton = findViewById(R.id.AddWordButton);
        submitButton = findViewById(R.id.submitButton);

        addWordButton.setOnClickListener(addWordlistener);
        submitButton.setOnClickListener(submitListener);


        // ROW 1
        button11 = findViewById(R.id.button1_1); // these are actually ET but i had them as buttons first and didnt want to rename
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


        DatabaseReference wordsRef = database.getReference("words");
        wordsRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (!task.getResult().exists()) {
                    String[] myWords = {"apple", "bread", "water", "lover", "taylor"};
                    for (String w : myWords) {
                        Word word = new Word();
                        word.setWord(w);
                        wordsRef.push().setValue(word);
                    }
                }
            }
        }); // end on complete listener


        // rn this is jsut running which i want
        //so single value event listener is lstengin for when its done fetching the data
        wordsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //runs even if data isn't changed- stupid name
                ArrayList<String> list = new ArrayList<>(); // this is global

                for (DataSnapshot child : snapshot.getChildren()) {
                    Word wordObj = child.getValue(Word.class);
                    if (wordObj != null) {
                        list.add(wordObj.getWord()); // its a hashmap in my thing ugh this makes it deal with object instead of string
                    }
                }

                if (!list.isEmpty()) {
                    String random = list.get(new Random().nextInt(list.size()));
                    secretWord= random.toLowerCase(); //making the random word the secret word variable then i want to check the letters against their selection
                    Log.i("LAUREN", secretWord);
                }
            } // end on data change

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    } //END ON CREATE

    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // i want to get a new word from the database and let the person know a new word is there
            // also to clear the input they put in
            // maybe ill start with a play button at the beginning and so it does it there too

        }
    }; // end reset listener

    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // now my random word is seceret word and should be checked against it

            char first  = secretWord.charAt(0); // getting the first .. letters of each thing splitting into char- then ill check each button against
            char second = secretWord.charAt(1);
            char third = secretWord.charAt(2);
            char fourth = secretWord.charAt(3);
            char fifth = secretWord.charAt(4);

            Log.i("LAURENWORDDD", String.valueOf(first) + String.valueOf(second));

            if (button11.getText().toString().equals(String.valueOf(first))){ //have to convert to string bc char never ==
                button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.green));
                totalPoints++;
            }
            if (button12.getText().toString().equals(String.valueOf(second))){
                button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.green));
                totalPoints++;
            }
            if (button13.getText().toString().equals(String.valueOf(third))){
                button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.green));
                totalPoints++;
            }
            if (button14.getText().toString().equals(String.valueOf(fourth))){
                button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.green));
                totalPoints++;
            }
            if (button15.getText().toString().equals(String.valueOf(fifth))){
                button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.green));
                totalPoints++;
            }

            //button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.green));
            //button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.gray));
            //button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.yellow));

        } // end on click
    };


    View.OnClickListener addWordlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), AddWordActivity.class);
            startActivity(intent);
        }
    }; // end on click listener
}
