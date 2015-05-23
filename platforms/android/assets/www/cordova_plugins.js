cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/com.nickza.audiorecorder/www/audiorecorder.js",
        "id": "com.nickza.audiorecorder.AudioRecorder",
        "clobbers": [
            "AudioRecorder"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.nickza.audiorecorder": "0.0.1"
}
// BOTTOM OF METADATA
});