<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<!-- reCAPTCHA Libary -->
	<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
	<title>Sign Up</title>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
	<!-- Font Icon -->
	<link rel="icon" type="image/png" href="images/icons/favicon.ico" />

	<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

	<!-- Main css -->
	<link rel="stylesheet" href="css/style.css">
	<script src='https://www.google.com/recaptcha/api.js'></script>
</head>

<body ng-app="UserManagement" ng-controller="UserController">

	<div class="main">

		<section class="signup">
			<!-- <img src="images/signup-bg.jpg" alt=""> -->
			<div class="container">
				<div class="signup-content">
					<form id="signup-form" class="signup-form">
						<h2 class="form-title">Create account</h2>
						<div class="form-group">
							<input type="text" class="form-input" ng-model="user.username" id="name"
								placeholder="Your Name" />
						</div>
						<div class="form-group">
							<input type="email" class="form-input" ng-model="user.email" id="email"
								placeholder="Your Email" />
						</div>
						<div class="form-group">
							<input type="text" class="form-input" ng-model="user.phone" id="phone"
								placeholder="Your Phone" />
						</div>
						<div class="form-group">
							<input type="password" class="form-input" ng-model="user.password" id="password"
								placeholder="Password" /> <span toggle="#password"
								class="zmdi zmdi-eye field-icon toggle-password"></span>
						</div>
						<!-- <div class="form-group">
							<input type="password" class="form-input" name="re_password"
								id="re_password" placeholder="Repeat your password" />
						</div>
						<div class="form-group">
							<input type="checkbox" name="agree-term" id="agree-term" class="agree-term" /> <label
								for="agree-term" class="label-agree-term"><span><span></span></span>I
								agree all statements in <a href="#" class="term-service">Terms
									of service</a></label>
						</div> -->
						<!-- reCAPTCHA -->
						<div  id="g-recaptcha" align="center" >
						</div>

						<div class="form-group">
							<button ng-click="submitUser()" type="submit" name="submit" id="submit"
								class="form-submit">Sign up</button>
						</div>
					</form>
					<p class="loginhere">
						Have already an account ? <a href="#" class="loginhere-link">Login
							here</a>
					</p>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="js/sign-up.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>