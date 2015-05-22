module.exports = {
  alert: function(title, message, buttonLabel, successCallback) {
    cordova.exec(successCallback,
                 null, // No failure callback
                 "AudioRecorder",
                 "alert",
                 [title, message, buttonLabel]);
  },
  start: function(filename, successCallback) {
    cordova.exec(successCallback,
                 null, // No failure callback
                 "AudioRecorder",
                 "start",
                 [filename]);
  },
  stop: function(successCallback) {
    cordova.exec(successCallback,
                 null, // No failure callback
                 "AudioRecorder",
                 "stop",
                 null);
  }};