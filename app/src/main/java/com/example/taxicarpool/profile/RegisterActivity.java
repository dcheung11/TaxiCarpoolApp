package com.example.taxicarpool.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taxicarpool.R;
import com.example.taxicarpool.data.AppDatabase;
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

    public void registerClick(View v){
        if (isEmpty(firstName)){
            firstName.setError("First Name is Required");
        }
        if (isEmpty(lastName)){
            lastName.setError("Last Name is Required");
        }

        if (!isEmail(email)){
            email.setError("E-mail is Required");
        }

        if (!isPassword(password)){
            password.setError("Please enter a password that is at least 6 characters long");
        }

        UserIdentity user = new UserIdentity(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        UserDao dao = db.Dao();
        dao.insertAll(user);
        System.out.println(user);
        System.out.println(dao.getAll());

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