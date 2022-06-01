routerApp.controller('listUserCtrl', function($scope, $http, $rootScope,
		$uibModal) {
	$http({
		method : 'GET',
		url : '/api/list-user'
	}).then(function successCallBack(response) {
		$rootScope.listUsers = response.data;

	}, function errorCallBack(response) {
		$scope.error = response.data;
	});

	$scope.showModal = function(id) {

		$http({
			method : 'GET',
			url : '/api/chi-tiet-nguoi-dung/' + id
		}).then(function successCallBack(response) {
			$scope.user = response.data;
			$uibModal.open({
				templateUrl : '/templates/user/popup/edit.html',
				controller : 'editController',
				scope : $scope,
				size : ''
			});

		}, function errorCallBack(response) {
			console.log("Error!");
		});

	}
	$scope.deleteUser = function(id) {
		// alert(id)

		$http({
			method : 'DELETE',
			url : '/api/xoa-nguoi-dung/' + id
		}).then(function successCallBack(response) {

			alert("User has deleted Successfully");

//			var index = $scope.listUsers.indexOf(id);
//			$scope.listUsers.splice(index, 1);
			$(".dataUser-" + id).fadeOut("slow");
		}, function errorCallBack(response) {
			console.log("Error!");
		});

	}
	$scope.addUser = function() {

		$uibModal.open({
			templateUrl : '/templates/user/popup/addUser.html',
			controller : 'addUserController'

		});

	}
});
routerApp.controller('editController', function($uibModalInstance, $scope,
		$rootScope, $http) {

	$scope.ok = function() {
		console.log($scope.user)
		$http({
			method : 'PUT',
			url : '/api/edit-user',
			data : $scope.user

		}).then(function successCallBack(response) {
			$uibModalInstance.dismiss("cancel");
			angular.forEach($rootScope.listUsers, function(v, k) {
				if (v.id === response.data.id) {
					$rootScope.listUsers[k] = response.data;
				}
			})

		}, function errorCallBack(response) {
			$scope.error = response.data;
		});
		// $uibModalInstance.close()
	}

	$scope.cancel = function() {

		$uibModalInstance.close()
	}
});
routerApp.controller('addUserController', function($uibModalInstance, $scope,
		$http) {

	$scope.add = function() {
		console.log($scope.user)
		$http({
			method : 'PUT',
			url : '/api/add-user',
			data : $scope.user

		}).then(function successCallBack(response) {
			alert("User has creats Successfully");
			$uibModalInstance.dismiss("cancel");

		}, function errorCallBack(response) {
			$scope.error = response.data;
		});
		// $uibModalInstance.close()
	}
	$scope.cancel = function() {

		$uibModalInstance.close()
	}
});
