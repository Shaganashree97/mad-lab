package com.shagana.lab3;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewMessage = findViewById(R.id.textViewMessage);

        // Get the message from intent
        String message = getIntent().getStringExtra("MESSAGE_KEY");
        textViewMessage.setText(message);
    }
}
