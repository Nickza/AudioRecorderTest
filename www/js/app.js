var app = angular.module('app', ['ngRoute','appControllers','ngTouch']);
app.config(['$routeProvider',
	function($routeProvider) {

		$routeProvider.
		when('/home', {
			templateUrl: 'partials/home.html',
		}).
		when('/setting', {
			templateUrl: 'partials/setting.html',
			controller: 'settingController'
		}).
		when('/recorder', {
			templateUrl: 'partials/recorder.html',
		}).
		when('/test', {
			templateUrl: 'partials/test.html',
			controller: 'testController'
		}).
		otherwise({
			redirectTo: '/home'
		});
	}]
);
var appControllers = angular.module('appControllers', []);

 
// As page loads-------------------------------------------------------------------------->
$(document).ready(function(){
	document.addEventListener("deviceready", onDeviceReady, false);
    function onDeviceReady() {
      document.addEventListener("backbutton", doBackbutton , false );
	};
	
});


function redirectPage(linkLocation) {
	window.location = linkLocation;
}	
	
function doBackbutton(e) {
	e.preventDefault();
	var loc = String(window.location).split("#").pop();
	if ( loc == '/home') {
		if (confirm("Exit App?")) navigator.app.exitApp();
	} else if ( loc == '/contacts' ||  loc == '/products'){
		redirectPage('#/home')
	} else {
		navigator.app.backHistory(); 
	};
}
