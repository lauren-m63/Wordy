package com.example.twitxclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.twitxclone.model.Word;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddWordActivity extends AppCompatActivity {

    Button submitButton;
    Button backButton;
    EditText wordInput;
    FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_word_activity);

        database = FirebaseDatabase.getInstance();

        wordInput = findViewById(R.id.addWordET); // assign EditText correctly
        submitButton = findViewById(R.id.submitButton);
        backButton = findViewById(R.id.backButton);

        submitButton.setOnClickListener(listener);
        backButton.setOnClickListener(backListener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String input = wordInput.getText().toString().trim(); // FIXED

            if (input.length() == 5) {
                DatabaseReference wordsRef = database.getReference("words");

                Word word = new Word();
                word.setWord(input.toLowerCase());

                wordsRef.push().setValue(word); //this is what adds it to database in the column words

                Toast.makeText(AddWordActivity.this, "Word added!", Toast.LENGTH_SHORT).show();

                wordInput.setText(""); // clear input after submission
            } else {
                Toast.makeText(AddWordActivity.this, "Word must be 5 letters", Toast.LENGTH_SHORT).show();
            }
        }
    };// end listener


    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MainWordleActivity.class);
            startActivity(intent);
        }
    }; // end back lsitener



} // last bracket
