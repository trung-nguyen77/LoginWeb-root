routerApp.controller('CartCtrl', function($scope, $http, $scope) {

	
	$scope.carts = JSON.parse(getCookie("cookieCart"));
	
	
	$scope.deleteCookie = function(index){
		var newCart;
		$scope.carts.splice(index, 1);
		newCart = $scope.carts;
		//console.log(newCart);
		
		setCookie("cookieCart", JSON.stringify(newCart) , 1);
	}
	
});
function setCookie(name, value, days) {
	var expires = "";

	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toUTCString();
	}
	document.cookie = name + "=" + (value || "") + expires + "; path=/";
}
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}
