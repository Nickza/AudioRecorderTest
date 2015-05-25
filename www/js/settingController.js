appControllers.controller('settingController', ['$scope', '$http', '$routeParams',
  function ($scope, $http, $routeParams) {
  
	$scope.controllerName = "settingController";
	$scope.$on('$viewContentLoaded', function() {
		$scope.getSetting();
	});

	$scope.getSetting = function() {
		$scope.settingString = localStorage.getItem('setting');
		$scope.setting = {};
		try {
			$scope.setting = JSON.parse($scope.settingString);
			if(typeof $scope.setting != 'object') $scope.setting = {};
	    } catch(err) {
			$scope.setting = {};
		}
	}
	
	$scope.saveSetting = function() {
	    $scope.settingString = JSON.stringify($scope.setting);
        localStorage.setItem('setting', $scope.settingString);	
		window.location.href='#/home';
	};
	
	$scope.clearContacts = function() {
		if (!confirm("Are you sure?  All your contacts will be permanently deleted")) return;
	    $scope.contactsString = JSON.stringify($scope.contacts);
        localStorage.setItem('contacts', []);
		alert('All your contacts have been deleted');
	};

}]);

 
