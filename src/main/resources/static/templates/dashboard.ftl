<html lang="vi">

<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png" />
<link rel="icon" type="image/png" href="assets/img/favicon.png" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Dashboard</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
	name='viewport' />

<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/css/paper-dashboard.css?v=2.0.0" rel="stylesheet" />
<link href="assets/demo/demo.css" rel="stylesheet" />
<link href="css/images.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="https://chipo.vn/static/web/css/bootstrap.min.css?ver=290920191" />

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/jquery-2.2.3.min.js?ver=290920191"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/bootstrap.min.js?ver=290920191"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/angular/angular.min.js?ver=290920191"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/angular/angular-animate.js?ver=290920191"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/angular/angular-sanitize.min.js?ver=290920191"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/angular/angular-messages.min.js?ver=290920191"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/angular/angular-resource.min.js?ver=290920191"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/angular/angular-ui-router.min.js?ver=290920191"></script>
<script type="text/javascript"
	src="https://chipo.vn/static/web/js/angular/ui-bootstrap-tpls-2.1.4.min.js?ver=290920191"></script>

<script src="js/app.js"></script>
<script src="js/user/list-user.js"></script>
<script src="js/products/list-products.js"></script>
<script src="js/products/search-products.js"></script>
<script src="js/cart/cart.js"></script>

</head>

<body ng-app="routerApp">
	<div class="wrapper ">
		<!-- <div layout:fragment="content"> -->
		<div class="sidebar" data-color="white" data-active-color="danger">

			<div class="logo">
				<a href="http://www.creative-tim.com" class="simple-text logo-mini">
					<div class="logo-image-small">
						<img src="assets/img/logo-small.png">
					</div>
				</a> <a href="/dang-xuat" class="simple-text logo-normal"> Đăng xuất
				</a>
			</div>
			<div class="sidebar-wrapper">
				<ul class="nav">
					<li><a href="#/list-user"> <i class="nc-icon nc-single-02"></i>
							<p>User Profile</p>
					</a></li>

					<li><a href="#/list-products"> <i
							class="nc-icon nc-spaceship"></i>
							<p>Products</p>
					</a></li>
					<li><a href="#/search-products"> <i
							class="nc-icon nc-zoom-split"></i>
							<p>Tiềm kiếm sản phẩm</p>
					</a></li>
					<li><a href="#/gio-hang"> <i class="nc-icon nc-cart-simple"
							></i>
							<p>Giỏ hàng</p>
					</a></li>
				</ul>
			</div>
		</div>

		<div class="main-panel ps-container ps-theme-default ps-active-y"
			data-ps-id="639c720d-57b4-cae5-a630-f67fc208cb27">

			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">
				<div class="container-fluid">
					<div class="navbar-wrapper">
						<div class="navbar-toggle">
							<button type="button" class="navbar-toggler">
								<span class="navbar-toggler-bar bar1"></span> <span
									class="navbar-toggler-bar bar2"></span> <span
									class="navbar-toggler-bar bar3"></span>
							</button>
						</div>
						<a class="navbar-brand" href="#pablo">Paper Dashboard</a>
					</div>
					<div class="collapse navbar-collapse justify-content-end"
						id="navigation"></div>
				</div>
			</nav>
			<!-- Content -->
			<div class="content">
				<ui-view></ui-view>
			</div>
		</div>

	</div>
	<!--   Core JS Files   -->
</body>

</html>
