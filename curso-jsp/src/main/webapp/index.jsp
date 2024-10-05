<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<title>Curso JSP</title>

<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 33%;
	right: 33%;
}

h5 {
	position: absolute;
	top: 30%;
	left: 33%;
}

.msg {
	position: absolute;
	top: 10%;
	left: 33%;
	font-size: 15px;
	color: #664d03;
	background-color: #fff3cd;
	border-color: #ffecb5;
}
</style>
</head>
<body>

	<h5>Bem vindo ao curso de JSP</h5>



	<form action="<%= request.getContextPath() %>/ServletLogin" method="post"
		class="row g-3 needs-validation" novalidate>

		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">

		<div class="col-mb-6">
			<label class="form-label" for="login"> Login </label><input class="form-control" id="login"
				name="Login" type="text" required>
				<div class="invalid-feedback">Por gentileza verificar o login inserido</div>
			<div class="valid-feedback">Ok!</div>
		</div>


		<div class="col-mb-3">
			<label class="form-label" for="senha"> Senha </label><input id="senha" class="form-control"
				name="senha" type="password" required>
			<div class="invalid-feedback">Por gentileza verificar a senha inserida</div>
			<div class="valid-feedback">Ok!</div>
		</div>


		<input class="btn btn-primary" type="submit" value="Acessar">


	</form>

	<h5 class="msg">${msg}</h5>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>

</body>
</html>