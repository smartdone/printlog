package com.smartdone.printlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("slog");
        int ret = Log.i("AAAAAA", "aaaaa");
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("access " + ret);
        Log.d("aaa", "this is a test");
        Log.i("aaa", "this is a test");
        Log.w("aaa", "this is a test");
        Log.v("aaa", "this is a test");
        Log.e("aaa", "this is a test");
    }
}
