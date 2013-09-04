package com.videoapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.videoandroid.R;

public class RecordVideo extends Activity {

	private static final int ACTION_TAKE_VIDEO = 2;
	private static String TAG = "RecordVideo";
	Button recordVideo;
	Button finish;
	Button play;
	VideoView videoView;
	Uri mostRecentUri;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d(TAG, "onCreate() RecordVideo");
        setContentView(R.layout.record_video_layout);
        
        videoView = (VideoView) findViewById(R.id.video_view);
        
        recordVideo = (Button) findViewById(R.id.record_video);
        recordVideo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dispatchTakeVideoIntent();
			}
        
        });
        Log.d(TAG, "got here before finish button");
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				CharSequence text = "Does nothing";
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
        
        });
        
        play = (Button) findViewById(R.id.play_video);
        play.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				playHardcodedVideo();
			}
        	
        });
        

    }
	
	private void playHardcodedVideo() {
		 Uri storedVideo = Uri
		 .parse("content://media/external/video/media/1057");
		 Log.d(TAG, storedVideo.toString());
		 playVideo(storedVideo);
	}
	
	private void dispatchTakeVideoIntent() {
	    Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
	    startActivityForResult(takeVideoIntent, ACTION_TAKE_VIDEO);
	    Log.d(TAG, "end of dispatchTakeVideoIntent()");
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri uri;
		Log.d(TAG, "onAcitivityResult() RecordVideo");
		if (resultCode == RESULT_OK) {
			if (requestCode == ACTION_TAKE_VIDEO) {
				playVideo(data);
			}
		} else if (resultCode == RESULT_CANCELED) {
			uri = null;
			Toast.makeText(getApplicationContext(), "Cancelled!",
					Toast.LENGTH_LONG).show();
		}

	}
    private void playVideo(Intent intent) {
    	Log.d(TAG, "Is videoview null: "+(videoView == null));
    	videoView.setVideoURI(intent.getData());
    	videoView.start();
    }
    private void playVideo(Uri uri) {
    	
    	videoView.setVideoURI(uri);
    	MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.requestFocus();
        videoView.start();
    }

	
}
