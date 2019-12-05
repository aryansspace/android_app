package com.example.finalproject;


import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.Switch;

import com.example.finalproject.ui.gallery.QuizActivity;

public class SettingsActivity extends AppCompatActivity {

    //public static final String KEY_PREF_EXAMPLE_SWITCH = "example_switch";
    private Switch switchDarkSetting, switchBoldSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .commit();

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quiz Settings");
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);



        switchDarkSetting = findViewById(R.id.darkSwitch);
        switchBoldSetting = findViewById(R.id.fontSwitch);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, QuizActivity.class));
    }
}
