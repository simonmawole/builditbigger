package com.pesapay.jokesdisplaylib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Simon on 14-Feb-17.
 */

public class JokesDisplayActivity extends AppCompatActivity {

    public static String JOKES_KEY = "JOKES";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokesdisplay);

        String jokes = "";
        jokes = getIntent().getStringExtra(JOKES_KEY);
        TextView tvDisplayJokes = (TextView) findViewById(R.id.tvDisplayJokes);
        tvDisplayJokes.setText(jokes);
    }
}
