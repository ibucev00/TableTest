package com.example.ivan.tabletest.Login_Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.tabletest.MainActivity;
import com.example.ivan.tabletest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText editMail;
    private EditText editPass;
    private EditText editPassRepeat;
    private Button buttonRegister;
    private Button buttonGoogleRegister;
    private TextView textLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        editMail = (EditText) findViewById(R.id.editMail);
        editPass = (EditText) findViewById(R.id.editPass);
        editPassRepeat = (EditText) findViewById(R.id.editPassRepeat);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonGoogleRegister = (Button) findViewById(R.id.buttonGoogleRegister);

        textLogIn = (TextView) findViewById(R.id.textLogIn);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        textLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser(){
        String email = editMail.getText().toString().trim();
        String password = editPass.getText().toString().trim();
        String password2 = editPassRepeat.getText().toString().trim();


        if(!(password.equals(password2))){
            Toast.makeText(RegisterActivity.this, "Pogrešno upisana lozinka", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        Toast.makeText(RegisterActivity.this, "Uspješna registracija", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Ne uspješna registracija", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
