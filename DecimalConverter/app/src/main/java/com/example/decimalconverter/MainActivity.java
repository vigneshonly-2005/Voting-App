package com.example.decimalconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText decimalInput;
    Button convertButton;
    TextView binaryResult, octalResult, hexResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalInput = findViewById(R.id.decimalInput);
        convertButton = findViewById(R.id.convertButton);
        binaryResult = findViewById(R.id.binaryResult);
        octalResult = findViewById(R.id.octalResult);
        hexResult = findViewById(R.id.hexResult);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = decimalInput.getText().toString();
                if (!input.isEmpty()) {
                    int decimal = Integer.parseInt(input);

                    String binary = Integer.toBinaryString(decimal);
                    String octal = Integer.toOctalString(decimal);
                    String hex = Integer.toHexString(decimal).toUpperCase();

                    binaryResult.setText("Binary: " + binary);
                    octalResult.setText("Octal: " + octal);
                    hexResult.setText("Hexadecimal: " + hex);
                }
            }
        });
    }
}