package com.example.twitxclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    EditText[][] allRows;
    int currentRow = 0;


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
    Button resetButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_wordle_activity);

        auth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();


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

        // array of edit text so i can keep track of what row im on i forgot abt
        allRows = new EditText[][] {
                {button11, button12, button13, button14, button15},
                {button21, button22, button23, button24, button25},
                {button31, button32, button33, button34, button35},
                {button41, button42, button43, button44, button45},
                {button51, button52, button53, button54, button55},
                {button61, button62, button63, button64, button65}
        };


        addWordButton = findViewById(R.id.AddWordButton);
        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.ResetButton);

        addWordButton.setOnClickListener(addWordlistener);
        submitButton.setOnClickListener(submitListener);
        resetButton.setOnClickListener(resetListener);



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

            DatabaseReference wordsRef = database.getReference("words");
            // im just pasting inefficient but whatevr
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

            for (int r = 0; r < allRows.length; r++) {
                for (int c = 0; c < allRows[r].length; c++) {
                    allRows[r][c].setText(""); // clear text
                    allRows[r][c].setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.white)); // reset color
                }
            }

// Reset to first row for new guesses
            currentRow = 0;
            totalPoints = 0;


        } // end on click
    }; // end reset listener

    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // now my random word is seceret word and should be checked against it

//            char first  = secretWord.charAt(0); // getting the first .. letters of each thing splitting into char- then ill check each button against
//            char second = secretWord.charAt(1);
//            char third = secretWord.charAt(2);
//            char fourth = secretWord.charAt(3);
//            char fifth = secretWord.charAt(4);

         //   Log.i("LAURENWORDDD", String.valueOf(first) + String.valueOf(second));

            // looping so i dont have to do every freaking one individual for every row crazy style
            // im going to loop for the each rows though im going to copy hmm
          //  EditText[] editTextsROW1 = {button11, button12, button13, button14, button15};
          //  char[] targetLetters = {first, second, third, fourth, fifth};

            if (currentRow >= allRows.length) return;

            EditText[] row = allRows[currentRow];           // current row
            char[] targetLetters = secretWord.toCharArray(); // secret word letters
            totalPoints = 0;                                 // reset for this guess

            // Loop through each EditText in the current row
            for (int i = 0; i < row.length; i++) {
                String input = row[i].getText().toString();

                if (input.equalsIgnoreCase(String.valueOf(targetLetters[i]))) {
                    row[i].setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.green));
                    totalPoints++;
                }
                else if (secretWord.contains(input)) {
                    // wrong position tellow time
                    row[i].setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.yellow));
                }
                else {
                    row[i].setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.gray));
                }
            }

            // Log points for debug
            Log.i("Wordle", "Points this guess: " + totalPoints);

            // Move to the next row for the next guess
            currentRow++;

            // Optionally check for win/lose
            if (totalPoints == targetLetters.length) {
                Log.i("Wordle", "win");
                Toast.makeText(v.getContext(), " you guessed it, hit the reset button to play again", Toast.LENGTH_SHORT).show();
            } else if (currentRow >= allRows.length) {
                Log.i("Wordle", "No more guesses! The word was: " + secretWord);
                Toast.makeText(v.getContext(), "not winner", Toast.LENGTH_SHORT).show();

            }
        }




            //button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.green));
            //button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.gray));
            //button11.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.yellow));

        } ;// end on click


    View.OnClickListener addWordlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), AddWordActivity.class);
            startActivity(intent);
        }
    }; // end on click listener
}
