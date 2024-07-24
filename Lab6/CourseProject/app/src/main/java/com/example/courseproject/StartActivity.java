package com.example.courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//Steps to start on a different activity than MainActivity
//in AndroidManifest.xml, need to change the location of the following code
//from applying to android:name=".MainActivity" to android:name=".StartActivity"
    /*
    <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
    */

//Steps to set up Firebase to Android Studio
//Tools -> Firebase -> Analytics -> Get started with Google Analytics (Java)
    //connect to Firebase -> redirected to browser
    //give a name (same name as project) -> continue -> continue
    //Canada (or keep same location) -> accept -> create project -> connect

    //Go to firebase.com
    //click on "Go to console" -> click on the created project
    //Build -> authentication -> email/password -> email/password enable -> save

//Tools -> Firebase -> Authentication -> Authenticate using Google (Java)
    //click on "Add the Firebase Authentication SDK to your app" -> accept changes
    //will see implementation(libs.firebase.auth) in build.gradle.kts

    //Go to firebase.com
    //click on "Go to console" -> click on the created project
    //Build -> Realtime Database -> create database -> next -> start in test mode

//Tools -> Firebase -> Realtime Database -> Get started with Realtime Database (Java)
    //click on "Add the Realtime Database SDK to your app" -> accept changes
    //will see implementation(libs.firebase.database) in build.gradle.kts

//in build.gradle.kts change minSdk = 21 to minSdk = 23

public class StartActivity extends AppCompatActivity {

    private Button registerButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);

        //get the views of resources
        registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });

        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });


    }
}