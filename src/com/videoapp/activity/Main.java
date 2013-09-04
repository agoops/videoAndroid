package com.videoapp.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Main extends Activity {
	
	
	
	String TAG = "Main Activity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i(TAG, "Main is created!");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		startActivity(new Intent(this, RecordVideo.class));
	}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                finish();
                break;
        }
    }
	
	
	
	
}

