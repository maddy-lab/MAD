package com.example.assignment01b;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTextTemperature;
    RadioGroup radioGroupConversion;
    TextView textViewConvertedTemperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // temperature conversion

        editTextTemperature = findViewById(R.id.editTextTemperature);
        radioGroupConversion = findViewById(R.id.radioGroupConversion);
        textViewConvertedTemperature = findViewById(R.id.textViewConvertedTemperature);

        // Temperature Conversion
        editTextTemperature = findViewById(R.id.editTextTemperature);
        radioGroupConversion = findViewById(R.id.radioGroupConversion);
        textViewConvertedTemperature = findViewById(R.id.textViewConvertedTemperature);
        findViewById(R.id.buttonReset).setOnClickListener(v -> {
            radioGroupConversion.check(R.id.radioButtonCtoF);
            editTextTemperature.setText("");
            textViewConvertedTemperature.setText("N/A");
        });
    }

    public void onCalculateClick(View v) {
        String enteredTempStr = editTextTemperature.getText().toString();

        if (enteredTempStr.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter a valid number !!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double enteredTemp = Double.parseDouble(enteredTempStr);
            int selectedConversion = radioGroupConversion.getCheckedRadioButtonId();
            double convertedTemperature;

            if (selectedConversion == R.id.radioButtonCtoF) {
                convertedTemperature = (enteredTemp * 9/5) + 32;
                textViewConvertedTemperature.setText( String.format("%.2f F",convertedTemperature));
            } else if (selectedConversion == R.id.radioButtonFtoC) {
                convertedTemperature = (enteredTemp - 32) * 5/9;
                textViewConvertedTemperature.setText( String.format("%.2f C",convertedTemperature));
            } else {
                Toast.makeText(this, "Select a conversion type!", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter a valid input!", Toast.LENGTH_SHORT).show();
        }
    }
}