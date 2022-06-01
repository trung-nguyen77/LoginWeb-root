// app.js
var routerApp = angular.module('routerApp', [ 'ui.router', 'ui.bootstrap',
		'ngAnimate', 'ngSanitize' ]);

routerApp.config(function($stateProvider) {

	$stateProvider
	.state('listUser', {
		url : "/list-user",
		templateUrl : '/templates/user/listUser.html',
		controller : 'listUserCtrl'

	})
	.state('listProducts', {
		url : "/list-products",
		templateUrl : '/templates/products/listProducts.html',
		controller : 'ProductsCtrl'
	})
	.state('search-Products', {
		url : "/search-products",
		templateUrl : '/templates/products/search-Products.html',
		controller : 'SearchProductsCtrl'

	})
	.state('cart-items', {
		url : "/gio-hang",
		templateUrl : '/templates/cart/cart.html',
		controller : 'CartCtrl'

	})
	;
});

routerApp.config(function($httpProvider) {

	$httpProvider.interceptors.push('HeaderService');
});
routerApp.factory('HeaderService', function() {

	return {
		request : function(config) {

			config.headers['Authorization'] = getCookie("cookie");

			return config;
		}
	};
	function getCookie() {
		function getCookie(name) {
			var value = "; " + document.cookie;
			var parts = value.split("; " + name + "=");
			if (parts.length === 2)
				return parts.pop().split(";").shift();
		}
	}

});