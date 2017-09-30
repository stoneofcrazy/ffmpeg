package com.fire.ffmpeg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fire.ffmpeg.utils.FileUtils;

public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText("asdfasdfasdfasdf");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outputurl= "rtmp://192.168.1.175/live/livestream";
                String inputurl= FileUtils.getFilePath()+"sintel.mp4";
                Log.e("zw", inputurl);
                stream(inputurl, outputurl);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native int stream(String inputurl, String outputurl);
}
