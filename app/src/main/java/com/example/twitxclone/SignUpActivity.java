package com.example.twitxclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.twitxclone.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    EditText dobText;
    FirebaseDatabase database;
    FirebaseAuth auth;
    OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
               DatabaseReference usersRef = database.getReference("users");
              String uid =  usersRef.push().getKey();
              User user= new User();
              user.setDob(dobText.getText().toString());
              user.setEmail(usernameText.getText().toString());
              usersRef.child(uid).setValue(user);
                Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_SHORT).show();

            }
            else{
                Exception e = task.getException();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "error user not created", Toast.LENGTH_SHORT).show();
            }

        } // end on complete
    }; // end on complete listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        usernameText = findViewById(R.id.user_field);
        passwordText = findViewById(R.id.pass_field);
        dobText = findViewById(R.id.dob_field);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

    } // end on create


    public void onSignUp(View view) {
        String usern = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String dob = dobText.getText().toString();

        // adding user input to the database as an object

        auth.createUserWithEmailAndPassword(usern, password).addOnCompleteListener(this, listener);

        Intent intent = new Intent(this, MessagesActivity.class);
        startActivity(intent);
    }

}