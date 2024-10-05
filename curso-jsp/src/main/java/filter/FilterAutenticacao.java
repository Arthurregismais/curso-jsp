package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.catalina.tribes.ChannelSender;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FilterAutenticacao extends HttpFilter implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
		super();

	}

	// Encerra os processos quando o servidor é parado
	// Mataria os processos de conexão com o banco
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Intercepta as requisições e as respostas no sistema
	// Tudo que for feito no sistema passa por aqui
	/*
	 * Validacao de autenticacao, dar commit e rollback de transacoes do banco,
	 * Validar e fazer redirecionamento de paginas
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlParaAutenticar = req.getServletPath(); // Url que esta sendo acessada

			// Validar se esta logado, se nao redireciona para a tela de login
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { // Nao esta
				// logado

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor, realize o login");
				redireciona.forward(request, response);
				return; // Para a execucao e redireciona para o login
			} else {

				chain.doFilter(request, response);
			}
			connection.commit(); // Deu tudo certo, commita as alteracoes no banco de dados
			
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// Executa os procssos ou recursos quando o servidor sobe o projeto
	// Inicia a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
