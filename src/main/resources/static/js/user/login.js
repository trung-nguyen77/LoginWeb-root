var login = angular.module("loginUser", []);

login.controller("loginUserCtrl", function($scope, $http) {
	$scope.loginUser = function() {

		console.log($scope.user);
	
		$http({
			method : 'POST',
			url : '/login',
			data : $scope.user,
			
		}).then(function successCallBack(response) {

			var token = response.data.accessToken;
//			document.cookie = "cookie = " + token;
		
			window.location.href = "http://localhost:8080/dashboard"
			console.log(token);
			
				});
	}
});

