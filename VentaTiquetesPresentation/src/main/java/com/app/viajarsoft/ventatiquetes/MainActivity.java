package com.app.viajarsoft.ventatiquetes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.testfairy.TestFairy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestFairy.begin(this, "b16175d0d65f677c363d4bdb11a477aba154070c");
    }
}
