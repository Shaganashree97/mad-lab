package com.shagana.lab2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button buttonColor, buttonFont, buttonSize;
    int colorIndex = 0, fontIndex = 0, sizeIndex = 0;

    int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK};
    String[] fonts = {"serif", "sans-serif", "monospace"};
    float[] sizes = {24f, 30f, 36f, 42f, 18f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        buttonColor = findViewById(R.id.buttonColor);
        buttonFont = findViewById(R.id.buttonFont);
        buttonSize = findViewById(R.id.buttonSize);

        buttonColor.setOnClickListener(v -> {
            textView.setTextColor(colors[colorIndex]);
            colorIndex = (colorIndex + 1) % colors.length;
        });

        buttonFont.setOnClickListener(v -> {
            textView.setTypeface(Typeface.create(fonts[fontIndex], Typeface.NORMAL));
            fontIndex = (fontIndex + 1) % fonts.length;
        });

        buttonSize.setOnClickListener(v -> {
            textView.setTextSize(sizes[sizeIndex]);
            sizeIndex = (sizeIndex + 1) % sizes.length;
        });
    }
}
