package com.example.myapplication01;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;




import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;



public class MainActivity extends AppCompatActivity {



    public Button create;
    public Button login;

    private EditText accountEdit;
    private EditText passwordEdit;
    private TextInputLayout accoutLayout;
    private TextInputLayout passwordLayout;


    private FirebaseAuth mAuth;

    private String myAccount;
    private String myPassword;
    //帳號所擁有的腳色個數
    private int rolesCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rolesCount = 0;


        create = findViewById(R.id.button);
        login = findViewById(R.id.button2);

        accountEdit = (EditText) findViewById(R.id.et_username);
        passwordEdit = (EditText) findViewById(R.id.et_password);
        accoutLayout = (TextInputLayout) findViewById(R.id.username);
        passwordLayout = (TextInputLayout) findViewById(R.id.password);
        passwordLayout.setErrorEnabled(true);
        accoutLayout.setErrorEnabled(true);


        // Initialize Firebase Auth

        mAuth = FirebaseAuth.getInstance();




            create.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, CreateActivity.class);
                    startActivity(intent);
                }
            });

            login.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check();
                }
            });



    }

    void check(){


            String account = accountEdit.getText().toString().trim();
            String password = passwordEdit.getText().toString().trim();

            if (account.length()>=6&&password.length()>=6){
                mAuth.signInWithEmailAndPassword(account, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Toast.makeText(MainActivity.this, "Authentication success!.",Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            } else {

                Toast.makeText(MainActivity.this, "帳號或密碼長度不足",
                        Toast.LENGTH_SHORT).show();
            }





    }

    //Change UI according to user data.
    public void  updateUI(FirebaseUser account){
        if(account != null){
            loginDio();
            myAccount = account.getEmail();
        }else {
            Toast.makeText(this,"登入失敗!",Toast.LENGTH_LONG).show();
        }
    }

    public void loginDio(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogTheme);
        dialog.setTitle("登入成功");
        dialog.setMessage("前往選擇角色");
        dialog.setNegativeButton("登出",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "已登出",Toast.LENGTH_SHORT).show();
            }

        });
        dialog.setPositiveButton("確定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

                if(rolesCount > 0) {
                    pickRoleDio();
                }else {
                    noRoleDio();
                }
            }

        });
        dialog.setCancelable(false);
        dialog.show();
    }
    public void pickRoleDio(){
        final String[] oldRoles = {"Peter","Scott","Lily","James"};
        AlertDialog.Builder dialog_list = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogTheme);
        dialog_list.setTitle("請選擇角色:");
        dialog_list.setItems(oldRoles, new DialogInterface.OnClickListener(){
            @Override

            //只要你在onClick處理事件內，使用which參數，就可以知道按下陣列裡的哪一個了
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, "你選的是" + oldRoles[which], Toast.LENGTH_SHORT).show();
            }
        });
        dialog_list.setCancelable(false);
        dialog_list.show();
    }

    private void noRoleDio(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogTheme);
        dialog.setTitle("尚未擁有角色");
        dialog.setMessage("前往創建角色");
        dialog.setNegativeButton("登出",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "已登出",Toast.LENGTH_SHORT).show();
            }

        });
        dialog.setPositiveButton("確定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CreareRoleActivity.class);
                startActivity(intent);

            }

        });
        dialog.setCancelable(false);
        dialog.show();
    }
}
