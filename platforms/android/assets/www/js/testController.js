appControllers.controller('testController', ['$scope', '$http', '$routeParams',
  function ($scope, $http, $routeParams) {
	$scope.controllerName = "testController";

  	//Contacts -------------------------------------------------------------------------------------------
	$scope.totalContacts = 0;
	$scope.contacts = [];
}]);