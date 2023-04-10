package com.example.taxicarpool.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;

public class EditActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText oldPassword;
    EditText newPassword;

    UserIdentity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        firstName = findViewById(R.id.edit_first_name);
        lastName = findViewById(R.id.edit_last_name);
        email = findViewById(R.id.edit_text_email);
        oldPassword = findViewById(R.id.edit_old_password);
        newPassword = findViewById(R.id.edit_new_password);
        user = LoggedInUser.getInstance().getUser();
    }

    public void editClick(View v) throws Exception {
        if (valid()){
            user.setFirstName(firstName.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(newPassword.getText().toString());
            EncryptionController.getInstance(this).updateUser(user);
            LoggedInUser.getInstance().login(user);
            Toast.makeText(this, "Edit Success", Toast.LENGTH_SHORT).show();
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

        if (!checkPassword(oldPassword)){
            oldPassword.setError("Passwords not the same");
            noError = false;
        }

        if (!isPassword(newPassword)){
            newPassword.setError("Please enter a password that is at least 6 characters long");
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

    boolean checkPassword(EditText text){
        String passwordCheck = text.getText().toString();
        String password = user.getPassword();
        return password.equals(passwordCheck);
    }



}