routerApp.controller('ProductsCtrl', function($scope, $http, $rootScope, $uibModal) {

	$http({
		method : 'GET',
		url : '/api/list-products'
	}).then(function successCallBack(response) {
		$rootScope.listProducts = response.data;

	}, function errorCallBack(response) {
		$scope.error = response.data;
	});
	
	$scope.addproducts = function() {

		$uibModal.open({
			templateUrl : '/templates/products/popup/addProducts.html',
			controller : 'addProductsController'

		});

	}
	$scope.deleteproducts = function(id) {
		// alert(id)

		$http({
			method : 'DELETE',
			url : '/api/delete-products/' + id
		}).then(function successCallBack(response) {

			alert("Products has deleted Successfully");

			var index = $scope.listProducts.indexOf(id);
			$scope.listProducts.splice(index, 1);
			$(".dataProducts-" + id).fadeOut("slow");
		}, function errorCallBack(response) {
			console.log("Error!");
		});

	}
	
	$scope.showImages = function(id){
//		alert(id);
		$http({
			method : 'GET',
			url : '/api/chi-tiet-san-pham/' + id
		}).then(function successCallBack(response) {
			console.log(response.data);
			$rootScope.products = response.data;
			$rootScope.pro = response.data.links[0];
			$uibModal.open({
				templateUrl : '/templates/products/popup/images-Result-Products.html',
				controller : 'imagesController',
				
			});
		}, function errorCallBack(response) {
			alert("Error!");
		});
	}
	
	
});
routerApp.controller('addProductsController', function($uibModalInstance, $scope,
		$http) {

	$scope.add = function() {
		console.log($scope.product)
		$http({
			method : 'POST',
			url : '/api/add-san-pham?stringUrl='+$scope.product.links,
			data : $scope.product

		}).then(function successCallBack(response) {
			alert("Products has creats Successfully");
			console.log(respone.data);
			$uibModalInstance.dismiss("cancel");
			
		}, function errorCallBack(response) {
			$scope.error = response.data;
		});
		// $uibModalInstance.close()
	}
	$scope.cancel = function() {

		$uibModalInstance.close()
	}
	
/*	$scope.showInforImages = function(images){
		
		var infor = images;
		alert(infor);
		
	}*/
});
routerApp.controller('imagesController', function($uibModalInstance, $scope,
		$http) {

	$scope.cancel = function() {

		$uibModalInstance.close()
	}
	
	$scope.showInforImages = function(images){

		$scope.pro = images;
	}
});
