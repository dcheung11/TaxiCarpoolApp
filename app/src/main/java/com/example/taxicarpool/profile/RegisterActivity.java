package com.example.taxicarpool.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.AppDatabase;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserDao;
import com.example.taxicarpool.data.UserIdentity;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
    }

    public void registerClick(View v) throws Exception {

        if (valid()){
            UserIdentity user = new UserIdentity(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());
            EncryptionController encryptionController = EncryptionController.getInstance(getApplicationContext());
            user.setUid(encryptionController.insertUser(user));
            LoggedInUser.getInstance().login(user);
            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    boolean valid(){
        boolean noError = true;
        if (isEmpty(firstName)){
            firstName.setError("First Name is Required");
            noError = false;
        }
        if (isEmpty(lastName)){
            lastName.setError("Last Name is Required");
            noError = false;
        }

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