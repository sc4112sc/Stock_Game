package com.example.myapplication01;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.Editable;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;



public class CreateActivity extends AppCompatActivity {

    public Button create;
    private FirebaseAuth mAuth;


    private EditText accountEdit;
    private EditText passwordEdit;
    private EditText passwordAgainEdit;
    private TextInputLayout accoutLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout passwordAgainLayout;
    private TextWatcher textWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);





        create = findViewById(R.id.button3);



        accountEdit = (EditText) findViewById(R.id.et_username);
        passwordEdit = (EditText) findViewById(R.id.et_password);
        passwordAgainEdit = (EditText) findViewById(R.id.et_password_again);
        accoutLayout = (TextInputLayout) findViewById(R.id.username);
        passwordLayout = (TextInputLayout) findViewById(R.id.password);
        passwordAgainLayout = (TextInputLayout) findViewById(R.id.password_again);
        passwordLayout.setErrorEnabled(true);
        accoutLayout.setErrorEnabled(true);
        passwordAgainLayout.setErrorEnabled(true);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        textWatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //after text changed
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                check();
            }
        };

        accountEdit.addTextChangedListener(textWatcher);
        passwordEdit.addTextChangedListener(textWatcher);
        passwordAgainEdit.addTextChangedListener(textWatcher);






        create.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString().trim();
                String passwordAgain = passwordAgainEdit.getText().toString().trim();
                if (account.length()>=6&&passwordAgain.length()>=6){
                        createCount(account,passwordAgain);
                }else {
                    Toast.makeText(CreateActivity.this, "發生錯誤！", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    void check(){





            String account = accountEdit.getText().toString().trim();
            String password = passwordEdit.getText().toString().trim();
            String passwordAgain = passwordAgainEdit.getText().toString().trim();
            if(TextUtils.isEmpty(account) || TextUtils.getTrimmedLength(account)<6){
                accoutLayout.setError("帳號必須至少為6位");

            }else{
                accoutLayout.setError("");
            }

            if(TextUtils.isEmpty(password) || TextUtils.getTrimmedLength(password)<6){
                passwordLayout.setError("密碼必須至少為6位");

            }else{
                passwordLayout.setError("");
            }

            if(TextUtils.isEmpty(passwordAgain) || !TextUtils.equals(passwordAgain, password) ){
                passwordAgainLayout.setError("密碼必須相同");

            }else{
                passwordAgainLayout.setError("");
            }










    }

    void createCount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            // Write a message to the database
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference(uid);

                            myRef.setValue("300000");

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(CreateActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });


    }
    //Change UI according to user data.
    public void  updateUI(FirebaseUser account){
        if(account != null){
            myDio();

        }else {
            Toast.makeText(this,"創建失敗!",Toast.LENGTH_LONG).show();
        }
    }

    public void myDio(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
        dialog.setTitle("創建成功");
        dialog.setMessage("請前往登入");
        dialog.setPositiveButton("確定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
        dialog.setCancelable(false);
        dialog.show();
    }
}
