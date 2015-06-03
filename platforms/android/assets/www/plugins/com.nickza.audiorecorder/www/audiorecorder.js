cordova.define("com.nickza.audiorecorder.AudioRecorder", function(require, exports, module) { module.exports = {
	alert: function(title, message, buttonLabel, successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "alert",
				 [title, message, buttonLabel]);
	},
	startRecording: function(filename, successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "startRecording",
				 [filename]);
	},
	stopRecording: function(successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "stopRecording",
				 []);
	},
	startPlaying: function(filename, successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "startPlaying",
				 [filename]);
	},
	stopPlaying: function(successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "stopPlaying",
				 []);
	},
	autoRecording: function(successCallback, errorCallback) {
		cordova.exec(successCallback,
				 errorCallback, 
				 "AudioRecorder",
				 "autoRecording",
				 []);
	},
	createStatusBarNotification : function(contentTitle, contentText, tickerText,successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "createStatusBarNotification",
				 [contentTitle, contentText, tickerText]);
	},
	updateNotification  : function(contentText, tickerText, number,successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "updateNotification ",
				 [contentText, tickerText, number]);
	},
	cancelNotification  : function(contentText,successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "cancelNotification ",
				 [contentText]);
	},
	showTickerText  : function(tickerText,successCallback) {
		cordova.exec(successCallback,
				 null, // No failure callback
				 "AudioRecorder",
				 "createStatusBarNotification",
				 [tickerText]);
	}
};
});
