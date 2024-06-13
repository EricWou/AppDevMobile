package com.example.reversestringproject;

import android.os.Bundle;
import android.view.PointerIcon;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputStringEditText;
    private TextView reverseStringTextView;
    private Button reverseStringButton;
    private String inputString;
    private String reverseString;

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

        reverseStringButton = (Button) findViewById(R.id.reverse_string_button);
        reverseStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Read content of inputStringEditText
                inputStringEditText = (EditText) findViewById(R.id.input_string_edit_text);

                //Do the reverse of the String (using Java logic)
                inputString = inputStringEditText.getText().toString();

                reverseString = "";

                for (int i=0;i<inputString.length();i++) {
                    reverseString += inputString.charAt(inputString.length()-i-1);
                }

                //Display the reverse content using reverseStringTextView
                reverseStringTextView = (TextView) findViewById(R.id.reverse_string_text_view);
                reverseStringTextView.setText(reverseString);
            }
        });
    }


}