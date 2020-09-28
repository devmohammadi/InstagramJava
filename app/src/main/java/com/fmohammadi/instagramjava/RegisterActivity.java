package com.fmohammadi.instagramjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText userName;
    private EditText name;
    private EditText email;
    private EditText password;
    private Button register;
    private TextView loginUser;

    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.etUserName);
        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        register = findViewById(R.id.btnRegister);
        loginUser = findViewById(R.id.login_user);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUserName = userName.getText().toString().trim();
                String txtName = name.getText().toString().trim();
                String txtEmail = email.getText().toString().trim();
                String txtPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(txtUserName) ||
                        TextUtils.isEmpty(txtName) ||
                        TextUtils.isEmpty(txtEmail) ||
                        TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(RegisterActivity.this, "please enter information", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() > 6) {
                    Toast.makeText(RegisterActivity.this, "your password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtUserName, txtName, txtEmail, txtPassword);
                }
            }
        });
    }

    private void registerUser(final String userName, final String name, final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

            }
        });
    }
}