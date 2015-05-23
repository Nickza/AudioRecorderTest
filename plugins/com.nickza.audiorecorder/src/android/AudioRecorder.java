package com.nickza.audiorecorder;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.os.Environment;
import android.content.Context;
import android.media.MediaRecorder;
import android.media.MediaPlayer;

import java.io.IOException;


public class AudioRecorder extends CordovaPlugin {
	protected void pluginInitialize() {
	}

	public MediaRecorder recorder;
   
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) 
	  throws JSONException {
	if (action.equals("alert")) {
	  alert(args.getString(0), args.getString(1), args.getString(2), callbackContext);
	  return true;
	}
	 else if (action.equals("start")) {
	  start(args.getString(0), callbackContext);
	  return true;
	}
	else if (action.equals("stop")) {
	  stopRecording(callbackContext);
	  return true;
	}
	return false;
	}

	private synchronized void alert(final String title, 
                                  final String message, 
                                  final String buttonLabel, 
                                  final CallbackContext callbackContext) {
		new AlertDialog.Builder(cordova.getActivity())
		.setTitle(title)
		.setMessage(message)
		.setCancelable(false)
		.setNeutralButton(buttonLabel, new AlertDialog.OnClickListener() {
		  public void onClick(DialogInterface dialogInterface, int which) {
			dialogInterface.dismiss();
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
		  }
		})
		.create()
		.show();
	}

    private synchronized void start(final String filename, 
                                    final CallbackContext callbackContext) {
		
		String file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
	//		Context context = cordova.getActivity().getApplicationContext();
	//		String file = context.getFilesDir().getPath().toString() + "/" + filename;
		try {
	        recorder = new MediaRecorder();
			recorder.reset();
			recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
			recorder.setOutputFile(file);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.prepare();
			recorder.start();
		} catch (IOException e) {
			alert("IO Error",e.getMessage(),"Ok", callbackContext);
		} catch (IllegalStateException e) {
			alert("State Error",e.getMessage(),"Ok", callbackContext);
    	}
	}

    private synchronized void stopRecording(final CallbackContext callbackContext) {
		recorder.stop(); 
		recorder.release(); 
        recorder = null;
	}
  
}
