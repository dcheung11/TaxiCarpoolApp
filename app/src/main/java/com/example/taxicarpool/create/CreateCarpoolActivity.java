package com.example.taxicarpool.create;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taxicarpool.R;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class CreateCarpoolActivity extends AppCompatActivity {
    Button button_scan;
    EditText taxi_id_input;
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_carpool);
        button_scan = findViewById(R.id.button_scan);
        button_scan.setOnClickListener(v -> {
            scanCode();
        });
        button_submit = findViewById(R.id.button_submit);
        taxi_id_input = findViewById(R.id.taxi_id_input);

    }

    protected void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up for flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        launcher.launch(options);
    }
    public void handleSubmit(View v){
        System.out.println("Ok");
        String s = taxi_id_input.getText().toString();
        Intent intent = new Intent(this, SelectAddressActivity.class);

//        Intent intent = new Intent(this, MapsActivity.class);
        if (isValidId()) {
//            Intent intent = new Intent(this, CarpoolDetailsInputActivity.class);
            intent.putExtra("MY_STRING_EXTRA", s);
            startActivity(intent);
        }



    }

    boolean isValidId(){
        String s = taxi_id_input.getText().toString();

        if (isEmpty(taxi_id_input) ){
            taxi_id_input.setError("Taxi ID Required");
//            System.out.println(s + " is INVALID ");
            return false;
        }
//        System.out.println(s + " is VALID ");
        return true;
    }

    boolean isEmpty(EditText text){
        CharSequence input = text.getText().toString();
        return TextUtils.isEmpty(input);
    }

    ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateCarpoolActivity.this);
            builder.setTitle("Result");
            builder.setMessage("Your ID is: " + result.getContents());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}