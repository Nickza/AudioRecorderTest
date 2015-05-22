package com.nickza.audiorecorder;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.media.MediaRecorder;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioRecorder extends CordovaPlugin {
  protected void pluginInitialize() {
  }

 // public MediaRecorder recorder;
  
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
      stop(callbackContext);
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
		
		alert(filename,"Press Ok to start recording","Ok", callbackContext);

		MediaRecorder recorder = new MediaRecorder();
		
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile(filename);
		//recorder.prepare();
		recorder.start();   // Recording is now started

	}

    private synchronized void stop(final CallbackContext callbackContext) {
		MediaRecorder recorder = new MediaRecorder();
		 recorder.stop(); 
		 recorder.release(); 
	}
  
}
