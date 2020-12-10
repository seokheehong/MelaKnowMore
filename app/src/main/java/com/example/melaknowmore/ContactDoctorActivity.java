package com.example.melaknowmore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ContactDoctorActivity extends AppCompatActivity {

    EditText recipientEditText, subjectEditText, messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_doctor);

        recipientEditText = (EditText) findViewById(R.id.editTextEmail);
        subjectEditText = (EditText) findViewById(R.id.editTextSubject);
        messageEditText = (EditText) findViewById(R.id.editTextMultiMessage);
    }

    public void sendEmail(View view) {
        String recipientList = recipientEditText.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = subjectEditText.getText().toString();
        String message = messageEditText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}