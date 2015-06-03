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
import android.content.Intent;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.content.BroadcastReceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import java.io.IOException;



public class AudioRecorder extends CordovaPlugin {
	protected void pluginInitialize() {
	}

	private MediaRecorder recorder = null;
    private MediaPlayer   player = null;
    private CallStateListener listener = null;
  
      final int notif_ID = 1234;
    NotificationManager notificationManager;
    Notification note;
    PendingIntent contentIntent;

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) 
	  throws JSONException {
		if (action.equals("alert")) {
			alert(args.getString(0), args.getString(1), args.getString(2), callbackContext);
			return true;
		}
		else if (action.equals("autoRecording")) {
			autoRecording(callbackContext);
			return true;
		}
		else if (action.equals("startRecording")) {
			startRecording(args.getString(0), callbackContext);
			return true;
		}
		else if (action.equals("stopRecording")) {
			stopRecording(callbackContext);
			return true;
		}
		else if (action.equals("startPlaying")) {
			startPlaying(args.getString(0), callbackContext);
			return true;
		}
		else if (action.equals("stopPlaying")) {
			stopPlaying(callbackContext);
			return true;
		}
		else if (action.equals("createStatusBarNotification")) { 
			createStatusBarNotification(args.getString(0), args.getString(1), args.getString(2), callbackContext); 
		} 
		else if (action.equals("updateNotification")) { 
			updateNotification(args.getString(0), args.getString(1), args.getInt(2)); 
		} 
		else if (action.equals("cancelNotification")) { 
			cancelNotification(); 
		} 
		else if (action.equals("showTickerText")) { 
			showTickerText(args.getString(0)); 
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

    private synchronized void startRecording(final String filename, 
                                    final CallbackContext callbackContext) {
		
		String file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
	//		Context context = cordova.getActivity().getApplicationContext();
	//		String file = context.getFilesDir().getPath().toString() + "/" + filename;
		try {
	        recorder = new MediaRecorder();
			recorder.reset();
			recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
			recorder.setOutputFile(file);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
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
  
    private synchronized void startPlaying(final String filename, 
                                    final CallbackContext callbackContext) {
		
		try {
			String file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
			player = new MediaPlayer();
            player.setDataSource(file);
            player.prepare();
            player.start();
		} catch (IOException e) {
			alert("IO Error",e.getMessage(),"Ok", callbackContext);
		} catch (IllegalStateException e) {
			alert("State Error",e.getMessage(),"Ok", callbackContext);
    	}
	}

    private synchronized void stopPlaying(final CallbackContext callbackContext) {
		player.release(); 
        player = null;
	}

	private synchronized void autoRecording(final CallbackContext callbackContext) {
	//	alert("Message","autoRecording","Ok", callbackContext);
        if (listener == null) {
	//	alert("Message","set Listener","Ok", callbackContext);
            listener = new CallStateListener();
            TelephonyManager TelephonyMgr = (TelephonyManager) cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            TelephonyMgr.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }
		listener.setCallbackContext(callbackContext);
        if (listener == null) {
	    }
	}

	//Notifications thanks to: saileshmittal/phonegap-system-notification-plugin 
	private void updateNotification(String contentTitle, String contentText, int number)
    {
		Context ctx = cordova.getActivity().getApplicationContext();
		note.setLatestEventInfo(ctx, contentTitle, contentText, contentIntent);
        note.number = number;
        notificationManager.notify(notif_ID,note);
    }

    private void createStatusBarNotification(String contentTitle, String contentText, String tickerText, final CallbackContext callbackContext)
    {
		Context ctx = cordova.getActivity().getApplicationContext();
        notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        note = new Notification(android.R.drawable.btn_star_big_on, tickerText, System.currentTimeMillis() );
		//change the icon
        
		Intent notificationIntent = new Intent(ctx,MyActivity.class);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent = notificationIntent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        contentIntent = PendingIntent.getActivity(ctx, 0, notificationIntent, 0);

        note.setLatestEventInfo(ctx, contentTitle, contentText, contentIntent);
		note.number = 1;  //Just created notification so number=1. Remove this line if you dont want numbers

        notificationManager.notify(notif_ID,note);
    }

    private void cancelNotification()
    {
        notificationManager.cancel(notif_ID);
    }

    private void showTickerText(String tickerText)
    {
        note.tickerText = tickerText;
        notificationManager.notify(notif_ID,note);
    }

	/*
    @Override
    public void onPause()
    {
        super.webView.loadUrl("javascript:navigator.systemNotification.onBackground();");
    }

    @Override
    public void onResume()
    {
        super.webView.loadUrl("javascript:navigator.systemNotification.onForeground();");
    }
	*/
	
}

class MyActivity extends Notification { 
}

class MyOutgoingCallHandler extends BroadcastReceiver {  

	public String PhoneNumber = "aa";

    public String getPhoneNumber() {
        return PhoneNumber;
    }

	@Override public void onReceive(Context context, Intent intent) {    
		// Extract phone number reformatted by previous receivers    
	//	PhoneNumber = getResultData();    
	//	if (PhoneNumber == null) {      
			// No reformatted number, use the original      
			this.PhoneNumber = "11"; //intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);    
	//		}
		// My app will bring up the call, so cancel the broadcast    
		//setResultData(null);    
		// Start my app to bring up the call    ...  
	}
}



class CallStateListener extends PhoneStateListener {

    private CallbackContext callbackContext;
	
	private MyOutgoingCallHandler outgoinging = new MyOutgoingCallHandler();

    public void setCallbackContext(CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
    }

    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        if (callbackContext == null) return;

        String msg = "";

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
            msg = "IDLE";
            break;

            case TelephonyManager.CALL_STATE_OFFHOOK:
            msg = "OFFHOOK,:" + ((incomingNumber != null) ? incomingNumber : "blank") + ":," + outgoinging.PhoneNumber;
            break;

            case TelephonyManager.CALL_STATE_RINGING:
            msg = "RINGING";
            break;
        }

        PluginResult result = new PluginResult(PluginResult.Status.OK, msg);
        result.setKeepCallback(true);

        callbackContext.sendPluginResult(result);
    }
	

}