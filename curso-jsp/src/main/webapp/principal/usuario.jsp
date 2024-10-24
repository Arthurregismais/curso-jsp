<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->

	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">




										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-header">

														<div class="card-block">
															<h4 class="sub-title">Cad. Usuário</h4>
															<form class="form-material"
																action="<%=request.getContextPath()%>/ServletUsuarioController"
																method="post" id="formUser">

																<input type="hidden" name="acao" id="acao" value="">

																<div class="form-group form-default form-static-label">
																	<input type="text" name="id" id="id"
																		class="form-control" readonly="readonly"
																		value="${modelLogin.id }"> <span
																		class="form-bar"></span> <label class="float-label">ID:</label>
																</div>
																<div class="form-group form-default">
																	<input type="text" name="nome" id="nome"
																		class="form-control" required="required"
																		value="${modelLogin.nome }"> <span
																		class="form-bar"></span> <label class="float-label">Nome:</label>
																</div>


																<div class="form-group form-default">
																	<input type="email" name="email" id="email"
																		class="form-control" required="required"
																		autocomplete="off" value="${modelLogin.email }">
																	<span class="form-bar"></span> <label
																		class="float-label">E-mail:</label>
																</div>

																<div class="form-group form-default">
																	<input type="text" name="login" id="login"
																		class="form-control" required="required"
																		autocomplete="off" value="${modelLogin.login }">
																	<span class="form-bar"></span> <label
																		class="float-label">Login</label>
																</div>

																<div class="form-group form-default">
																	<input type="password" name="senha" id="senha"
																		class="form-control" required="required"
																		autocomplete="off" value="${modelLogin.senha }">
																	<span class="form-bar"></span> <label
																		class="float-label">Password:</label>
																</div>
																<button type="button"
																	class="btn btn-primary waves-effect waves-light"
																	onclick="limparForm();">Novo</button>
																<button class="btn btn-success waves-effect waves-light">Salvar
																</button>
																<button type="button"
																	class="btn btn-info waves-effect waves-light"
																	onclick="criarDeleteComAjax();">Excluir</button>

															</form>

														</div>
													</div>
												</div>
											</div>
											<span id="msg">${msg}</span>

										</div>
										<!-- Page-body end -->
									</div>
									<div id="styleSelector"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="javascriptfile.jsp"></jsp:include>
	<script type="text/javascript">
		function limparForm() {
			var elementos = document.getElementById("formUser").elements; // Retorna os elementos HTML dentro do Form

			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = "";
			}
		}

		function criarDelete() {

			if (confirm('Deseja realmente excluir os dados?')) {
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
			}

		}

		function criarDeleteComAjax() {

			if (confirm('Desja realmente excluir os dados?')) {
				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({
					method : "get",
					url : urlAction,
					data : "id=" + idUser + '&acao=deletarajax',
					success : function(response) {
						limparForm();
						document.getElementById('msg').textContent = response;

					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar o usuário por id: '
									+ xhr.responseText);
						})
			}
		}
	</script>
</body>
</html>
