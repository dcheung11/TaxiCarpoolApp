package com.example.taxicarpool.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;
import com.example.taxicarpool.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        login = findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    login();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }





    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    void login() throws Exception {
        if(valid()) {
            UserIdentity loginAttempt;
            loginAttempt = EncryptionController.getInstance(getApplicationContext()).findByEmail(email.getText().toString());
            if (loginAttempt==null){
                Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                return;
            }
            if (loginAttempt.getPassword().equals(password.getText().toString())){
                LoggedInUser.getInstance().login(loginAttempt);
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                finish();
            } else{
                password.setError("Password does not match");
            }
        }
    }

    boolean valid(){
        boolean noError = true;

        if (!isEmail(email)){
            email.setError("E-mail is Required");
            noError = false;
        }

        if (!isPassword(password)){
            password.setError("Please enter a password that is at least 6 characters long");
            noError = false;
        }
        return noError;
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isPassword(EditText text) {
        CharSequence password = text.getText().toString();
        return (!TextUtils.isEmpty(password) && password.length() >= 6);
    }

    boolean isEmpty(EditText text){
        CharSequence input = text.getText().toString();
        return TextUtils.isEmpty(input);
    }
}