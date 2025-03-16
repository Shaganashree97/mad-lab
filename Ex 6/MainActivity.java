package com.shagana.lab6;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView threadTextView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threadTextView = findViewById(R.id.threadTextView);
        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(v -> {
            // Start both threads on button click
            new MyThread().start();  // Using extends Thread
            new Thread(new MyRunnable()).start();  // Using implements Runnable
        });
    }

    // Method 1: Using Thread class
    class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                final int count = i;
                handler.post(() -> threadTextView.append("\nThread Class: " + count));
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method 2: Using Runnable Interface
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                final int count = i;
                handler.post(() -> threadTextView.append("\nRunnable: " + count));
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
