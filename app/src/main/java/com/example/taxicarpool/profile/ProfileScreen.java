package com.example.taxicarpool.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.MainActivity;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;

public class ProfileScreen extends AppCompatActivity {

    TextView firstName;
    TextView lastName;
    TextView email;
    UserIdentity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        user = LoggedInUser.getInstance().getUser();
        firstName = findViewById(R.id.profile_screen_first_name);
        String field1 = "First Name: " + user.getFirstName();
        firstName.setText(field1);
        lastName = findViewById(R.id.profile_screen_last_name);
        String field2 = "Last Name: " + user.getLastName();
        lastName.setText(field2);
        email = findViewById(R.id.profile_screen_email);
        String field3 = "Email: " + user.getEmail();
        email.setText(field3);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void delete(View v){
        LoggedInUser.getInstance().logoff();
        EncryptionController.getInstance(this).deleteUser(user);
        Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show();
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}