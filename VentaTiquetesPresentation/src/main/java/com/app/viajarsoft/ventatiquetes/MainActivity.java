package com.app.viajarsoft.ventatiquetes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.testfairy.TestFairy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestFairy.begin(this, "9cfa7e8493314cae6d7d6265cff0399be9cb4b15");
    }
}
