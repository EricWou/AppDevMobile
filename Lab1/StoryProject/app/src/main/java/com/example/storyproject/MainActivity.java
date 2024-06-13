package com.example.storyproject;

import android.os.Bundle;
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

    private EditText nameEditText;
    private EditText ageEditText;
    private EditText cityEditText;
    private EditText collegeEditText;
    private EditText professionEditText;
    private EditText animalEditText;
    private EditText petNameEditText;
    private TextView storyOutputTextView;
    private Button displayStoryButton;


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

        displayStoryButton = findViewById(R.id.displayStory_button);
        displayStoryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                nameEditText = findViewById(R.id.name_edit_text);
                ageEditText = findViewById(R.id.age_edit_text);
                cityEditText = findViewById(R.id.city_edit_text);
                collegeEditText = findViewById(R.id.college_edit_text);
                professionEditText = findViewById(R.id.profession_edit_text);
                animalEditText = findViewById(R.id.animal_edit_text);
                petNameEditText = findViewById(R.id.petName_edit_text);

                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String city = cityEditText.getText().toString();
                String college = collegeEditText.getText().toString();
                String profession = professionEditText.getText().toString();
                String animal = animalEditText.getText().toString();
                String petName = petNameEditText.getText().toString();

                storyOutputTextView = findViewById(R.id.storyOutput_text_view);

                String story = "There once was a person named "+name+" who lived in "
                        +city+". At the age of "+age+", "+name+" went to " +
                        "college at "+college+". "+name+" graduated and went to " +
                        "work as a "+profession+". Then, "+name+" adopted a(n) " +
                        animal+" named "+petName+". They both lived happily ever after!";

                storyOutputTextView.setText(story);

            }
        });
    }
}