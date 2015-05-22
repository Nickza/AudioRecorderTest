cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/com.nickza.audiorecorder/www/audiorecorder.js",
        "id": "com.nickza.audiorecorder.AudioRecorder",
        "clobbers": [
            "AudioRecorder"
        ]
    },
    {
        "file": "plugins/cordova-plugin-whitelist/whitelist.js",
        "id": "cordova-plugin-whitelist.whitelist",
        "runs": true
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.nickza.audiorecorder": "0.0.1",
    "cordova-plugin-whitelist": "1.0.0"
}
// BOTTOM OF METADATA
});