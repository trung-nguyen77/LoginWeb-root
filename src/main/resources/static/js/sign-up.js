var app = angular.module("UserManagement", []);

// Controller Part
app.controller("UserController", function($scope, $http) {
    
	setTimeout(() => {
		
		$scope.widgetId = grecaptcha.render('g-recaptcha', {
	        'sitekey': '6LeeyrgUAAAAAKGHVfIZciOviKqMDyJyc1b9Z2lq'
	    });
	}, 200);

	$scope.submitUser = function() {
		var uInput = $scope.user;
		
		uInput.captcha = grecaptcha.getResponse();
		
		method = 'PUT';
		url = '/sign-up';
		 $http({
		 method: method,
		 url: url,
		 data: $scope.user,
		 headers: {
		 'Content-Type': 'application/json'
		 }
		 }).then(_success, _error);
	};

	function _success(res) {
		alert("Success");
		window.location.href = "http://localhost:8080/dang-nhap";
	}

	function _error(res) {
		var data = res.data;
		var status = res.status;
		var header = res.header;
		var config = res.config;
		alert("Error: " + status + ":" + data);
	}
	function _clearFormData() {
		$scope.user.name = "";
		$scope.user.phone = "";
		$scope.user.email = "";
		$scope.user.password = "";
	}
	;
});