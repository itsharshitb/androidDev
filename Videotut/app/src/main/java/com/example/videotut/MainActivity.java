package com.example.videotut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //we are coding here because we want to play the video as we open the app...
        // we are attaching the video here look how..
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        //setting the address of the video
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" +R.raw.demo); // remember it as https:// on web?? ok
        //R.raw.demo is Resource.raw(folder of the video).video_name

        //lets run
        //lets see the prob    ok looks nice
        //so this time we have no controls like pause play forwad or backward
        //lets make those available
        MediaController media = new MediaController(this);  //this keyword represent that we are using this mediocontroller for this app only
        media.setAnchorView(videoView); //set anchorview will set the position of the control bar according to the element passed(videoView in this)
        videoView.setMediaController(media);    //this will visible the controller on screen...
        //lets see
        videoView.start();  // to start the video
        //thanks for watching
    }
}