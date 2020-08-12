package com.suza.labexam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {
    EditText fnameEditText, lnameEditText, ageEditText;
    Button createButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        getSupportActionBar().setTitle("Register Candidate");

        fnameEditText = findViewById(R.id.fname_text);
        lnameEditText = findViewById(R.id.lname_text);
        ageEditText   = findViewById(R.id.age_text);
        createButton  = findViewById(R.id.save_btn);
        backButton    = findViewById(R.id.back_btn);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = fnameEditText.getText().toString();
                String lname = lnameEditText.getText().toString();
                String ageString = ageEditText.getText().toString();

                if (fname.equals("") || lname.equals("") || ageString.equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are Required.", Toast.LENGTH_SHORT).show();
                } else {
                    Database db = new Database(CreateActivity.this);
                    db.register(fname, lname, Integer.parseInt(ageString));

                    fnameEditText.setText("");
                    lnameEditText.setText("");
                    ageEditText.setText("");

                    fnameEditText.requestFocus();
                    Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}