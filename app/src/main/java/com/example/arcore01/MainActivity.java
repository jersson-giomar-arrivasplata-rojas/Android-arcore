package com.example.arcore01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.ar.core.ArCoreApk;

public class MainActivity extends AppCompatActivity {
    Button btnTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable AR related functionality on ARCore supported devices only.
        maybeEnableArButton();
        setContentView(R.layout.activity_main);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraitLayout);
        btnTag=new Button(this);
        btnTag.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        btnTag.setText("Button 1");
        layout.addView(btnTag);

    }
    void maybeEnableArButton() {
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(this);
        if (availability.isTransient()) {
            // Re-query at 5Hz while compatibility is checked in the background.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    maybeEnableArButton();
                }
            }, 200);
        }
        if (availability.isSupported()) {
            btnTag.setVisibility(View.VISIBLE);
            btnTag.setEnabled(true);
            // indicator on the button.
        } else { // Unsupported or unknown.
            btnTag.setVisibility(View.INVISIBLE);
            btnTag.setEnabled(false);
        }
    }
}
