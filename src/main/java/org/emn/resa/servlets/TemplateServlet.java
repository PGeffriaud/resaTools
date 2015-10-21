package org.emn.resa.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.resa.managers.RessourceManager;
import org.emn.resa.managers.UserManager;
import org.emn.resa.managers.ConnexionManager;

/**
 * Servlet implementation class TemplateServlet
 */
@WebServlet("/action/*")
public class TemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TemplateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullPath = request.getPathInfo().substring(1);
		String[] paths = fullPath.split("/");

		switch (paths[0]) {
		case "ressources":
			if (paths.length > 1) {
				switch (paths[1]) {
				case "addtype":
					RessourceManager.addType(request.getParameter("typeName"));
					break;
				case "deltype":
					RessourceManager.deleteType(Integer.parseInt(request.getParameter("id")));
					break;
				case "updatetype":
					break;
				case "address":
					break;
				case "delress":
					break;
				case "updateress":
					break;
				default:
					break;
				}
			}
			request.getSession().setAttribute("listType", RessourceManager.getTypeList());
			break;
		case "reservations":
			break;
		case "user":
			if (paths.length > 1) {
				switch (paths[1]) {
//				case "addUser":
//					RessourceManager.addType(request.getParameter("typeName"));
//					break;
				case "delUser":
					if(request.getParameter("delUserButton") != null){
						UserManager.deleteUser(request.getParameter("delUserButton"));
					}
					break;
				default:
					break;
				}
			}
			request.getSession().setAttribute("listUser", UserManager.getUserList());
			break;
		case "login":
			handleConnexion(request, response);
			break;
		case "logout":
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/action/login");
			break;
		default:
			break;
		}

		if (!paths[0].equals("login") && !paths[0].equals("logout")) {
			request.setAttribute("page", paths[0]);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void handleConnexion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		if (ConnexionManager.connect(login, pass) != null) {
			request.getSession().setAttribute("currentSessionUser", ConnexionManager.connect(login, pass));
			request.setAttribute("page", "accueil");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else {
			if (login != null) {
				request.getSession().setAttribute("connectedTried", true);
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
		}
	}
}
