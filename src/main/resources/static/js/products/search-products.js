routerApp
		.controller(
				'SearchProductsCtrl',
				function($scope, $http, $uibModal, $rootScope) {

					$scope.searchProducts = function(id) {
						var id = id;

						$http({

							method : 'POST',
							url : '/api/add-san-pham?stringUrl=' + id,
							/* + encodeURIComponent($scope.product.links), */
							data : $scope.product
							
						})
								.then(
										function successCallBack(response) {							
											
											$rootScope.products = response.data;
											$rootScope.pro = response.data.links[0];
											$rootScope.quantity = 1;
											$uibModal
													.open({
														
														templateUrl : '/templates/products/popup/search-Result.html',
														controller : 'cancelController'
														
													});
										})
					}
				});

var list =[];
if(getCookie("cookieCart") && getCookie("cookieCart") != null && getCookie("cookieCart") != ""){
	list = JSON.parse(getCookie("cookieCart"));
}
routerApp.controller('cancelController', function($uibModalInstance, $scope, $rootScope) {
	
	$scope.abc = $rootScope.products;
	console.log($scope.abc);
	
	$scope.cancel = function() {
		$uibModalInstance.close()
	}
	$scope.showInforImages = function(images) {
		
		$scope.pro = images;
	}
	$scope.test = function () {
		var data = $scope.abc;
		/* console.log(data.properSkus); */
                setTimeout(function () {
                    // selectProperties();
                    $(".btn").click(function () {
                        var titleCheck = $(this).attr("titleChecks");
                        if ($(this).hasClass("active")) {
                            $(this).removeClass("active");
                        } else {
                        	/* console.log(titleCheck); */
                            $("[titleChecks=" + titleCheck + "]").removeClass("active");
                            $(this).addClass("active");
                        }
                        var textCheckId = "";
                        var titleee = "";
                        setTimeout(function () {
                        	  $(".active").each(function () {
                        		 
                                  textCheckId += $(this).attr("idChecks") + ";";
                                  titleee += $(this).attr("titlee") + "-"
                              });
                        	  
                        	  textCheckId = textCheckId.substring(0, textCheckId.length - 1);
                        	  console.log(textCheckId);
                              for (id of data.properSkus) {
                              	if(id.propPath === textCheckId){
                              		
                              	  for (idSku2info of data.propertySku2infos) {
                              		if(idSku2info.idSku2info === id.skuId){
                              			$(".priceProduct").addClass("hidden");
                                  		$(".priceNew").removeClass("hidden");
                                      	$(".priceNew").text(idSku2info.priceText + " ~ " + idSku2info.priceText * 3.365 + " VNĐ");
                                      	
                                  		$(".quantity").removeClass("hidden");
                                  		$(".newquantity").text(idSku2info.quantity);
                                  		
                                  		
                                  		break;
                              		}	                             		
                              	  }                             		
                              	}
                              	
                              }
                        },500)
                    });
                }, 500);
           
    };
  
    $scope.countDown = function(down){
    	
    	down--;
    	$scope.quantity = down--;
    	
    }
    $scope.countUp = function(up){
    	up++;
    	$scope.quantity = up++;
    }
    
    $scope.addCartItems = function(){
    	var data = $scope.abc;
    	var textCheckId = "";
    	var title="";
    	var images="";
        var cartItems = {};
        	cartItems.name = data.nameProduct;
        	cartItems.quantity = $scope.quantity;
            
         	  $(".active").each(function () {
                   textCheckId += $(this).attr("idChecks") + ";";
                   title += $(this).attr("title") + ", ";
                   // images = $(this).attr("images")
               });
         	  if($(".active")){
         		 images = $(".active img").attr("src");
         		 cartItems.images = images;
               }
         	 title = title.substring(0, title.length - 2);
         	
         	 cartItems.property = title;
         	 textCheckId = textCheckId.substring(0, textCheckId.length - 1);
         	  console.log(textCheckId);
               for (id of data.properSkus) {
               	if(id.propPath === textCheckId){               		
               	  for (idSku2info of data.propertySku2infos) {
               		if(idSku2info.idSku2info === id.skuId){
               			cartItems.price = idSku2info.priceText * 3.365;	
                   		break;
               		}	                             		
               	  }                             		
               	}
               }
               console.log(cartItems);
               alert("Đã thêm một sản phẩm vào giỏ hàng.");
       
               list.push(cartItems);
               console.log(cartItems);
               setCookie("cookieCart", JSON.stringify(list) , 1);
    }
    $scope.toCartItems = function(){

    	window.location.href = "http://localhost:8080/dashboard#/gio-hang";
    	
    	$('.modal-images').hide();
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
function checkCookie() {
	  var username = getCookie("username");
	  if (username != "") {
	   alert("Welcome again " + username);
	  } else {
	    username = prompt("Please enter your name:", "");
	    if (username != "" && username != null) {
	      setCookie("username", username, 365);
	    }
	  }
	}