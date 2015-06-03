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
    },
    {
        "file": "plugins/io.gvox.plugin.phonecalltrap/www/PhoneCallTrap.js",
        "id": "io.gvox.plugin.phonecalltrap.PhoneCallTrap",
        "clobbers": [
            "window.PhoneCallTrap"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.device/www/device.js",
        "id": "org.apache.cordova.device.device",
        "clobbers": [
            "device"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "android.support.v4": "21.0.1",
    "com.nickza.audiorecorder": "0.0.1",
    "cordova-plugin-whitelist": "1.0.0",
    "io.gvox.plugin.phonecalltrap": "0.1.0",
    "org.apache.cordova.device": "0.3.0"
}
// BOTTOM OF METADATA
});