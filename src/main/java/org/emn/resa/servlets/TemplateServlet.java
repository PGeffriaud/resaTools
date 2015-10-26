package org.emn.resa.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.resa.entities.User;
import org.emn.resa.managers.ConnexionManager;
import org.emn.resa.managers.ResaManager;
import org.emn.resa.managers.RessourceManager;
import org.emn.resa.managers.UserManager;
import org.emn.resa.utils.ReservationView;
import org.h2.util.DateTimeUtils;
import org.hibernate.transform.ToListResultTransformer;

import antlr.StringUtils;

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
					String[] types = request.getParameterValues("selectType");
					String name = request.getParameter("nameRess");
					String desc = request.getParameter("textRess");
					RessourceManager.addRessource(name, desc, types);
					break;
				case "delress":
					String idRess = request.getParameter("delRessButton");
					if(idRess != null){
						RessourceManager.deleteRessource(Integer.parseInt(idRess));
					}
				case "updateress":
					break;
				default:
					break;
				}
			}
			String nameSearch = request.getParameter("ressSearch");
			request.getSession().setAttribute("listType", RessourceManager.getTypeList());
			request.getSession().setAttribute("listRess", RessourceManager.getRessourceList(nameSearch));
			break;
		case "reservations":
			if (paths.length > 1) {
				switch (paths[1]) {
					case "goresa":
						request.setAttribute("nameRess",request.getParameter("goResaName"));
						request.setAttribute("idRess", request.getParameter("goResaId"));
						break;
					case "addresa":
						int idUser = Integer.parseInt(request.getParameter("idUser"));
						int idRess = Integer.parseInt(request.getParameter("idRess"));
					try {
						Date from = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dateResaFrom"));
						Date to = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dateResaTo"));
						ResaManager.addReservation(idUser, idRess, from, to);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					default: break;
				}
			}
			List<ReservationView> resaList = ResaManager.getResaList();
			String userLogin = ((User)request.getSession().getAttribute("currentSessionUser")).getLogin();
			request.getSession().setAttribute("listResa", resaList);
			break;
		case "user":
			if (paths.length > 1) {
				switch (paths[1]) {
				case "addUser":
					request = UserManager.addUser(request);
					break;
				case "delUser":
					if(request.getParameter("delUserButton") != null){
						UserManager.deleteUser(request.getParameter("delUserButton"));
					}
					break;
				case "updateUser":
					request = UserManager.modifyUser(request);
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
				request.setAttribute("connexionTried", true);
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
		}
	}
}
