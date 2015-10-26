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
					boolean ok = RessourceManager.addType(request.getParameter("typeName"));
					if(ok) request.setAttribute("validationMessage", "Le nouveau type de ressource a bien été enregistré");
					else request.setAttribute("errorMessage", "Le type n'a pas un nom valide: vide ou existe déjà");
					break;
				case "deltype":
					boolean delOk = RessourceManager.deleteType(Integer.parseInt(request.getParameter("id")));
					if(delOk) request.setAttribute("validationMessage", "Suppression du type effectuée");
					else request.setAttribute("errorMessage", "Erreur d'intégrité: la suppression du type est impossible");
					break;
				case "updatetype":
					boolean updOk = RessourceManager.modifyType(request.getParameter("typeName"), request.getParameter("id"));
					if(updOk) request.setAttribute("validationMessage", "Modification enregistrée");
					else request.setAttribute("errorMessage", "Erreur lors de la mise à jour du type");
					break;
				case "address":
					String[] types = request.getParameterValues("selectType");
					String name = request.getParameter("nameRess");
					String desc = request.getParameter("textRess");
					RessourceManager.addRessource(name, desc, types);
					request.setAttribute("validationMessage", "Ressource enregistrée");
					break;
				case "delress":
					String idRess = request.getParameter("delRessButton");
					if(idRess != null){
						boolean delRessOk = RessourceManager.deleteRessource(Integer.parseInt(idRess));
						if(delRessOk) request.setAttribute("validationMessage", "Suppression de la ressource effectuée avec succès");
						else request.setAttribute("errorMessage", "La ressource est en cours de réservation: suppression impossible");
						
					}
					break;
				case "updateress":
					String[] modifiedTypes = request.getParameterValues("selectType");
					String modifiedName = request.getParameter("nameRess");
					String modifiedDesc = request.getParameter("textRess");
					String id = request.getParameter("id");
					RessourceManager.modifyRessource(id, modifiedTypes, modifiedName, modifiedDesc);
					request.setAttribute("validationMessage", "Mise à jour de la ressource effectuée avec succès");
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
							boolean addOk = ResaManager.addReservation(idUser, idRess, from, to);
							if(addOk) request.setAttribute("validationMessage", "Réservation enregistrée");
							else request.setAttribute("errorMessage", "La ressource n'a pas pu être réservée: dates invalides");
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "delresa":
						int idResa = Integer.parseInt(request.getParameter("delRessButton"));
						ResaManager.deleteReservation(idResa);
						request.setAttribute("validationMessage", "Réservation annulée");
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
					boolean addOk = handleUser(request, false);
					if(addOk) request.setAttribute("validationMessage", "Utilisateur enregistré");
					else request.setAttribute("errorMessage", "Un utilisateur existe déjà avec ce login");
					break;
				case "delUser":
					if(request.getParameter("delUserButton") != null){
						UserManager.deleteUser(request.getParameter("delUserButton"));
						request.setAttribute("validationMessage", "Suppression enregistrée");
					}
					break;
				case "updateUser":
					boolean updateOk = handleUser(request, true);
					if(updateOk) request.setAttribute("validationMessage", "Modification enregistrée");
					else request.setAttribute("errorMessage", "La modification a echouée");
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

	private boolean handleUser(HttpServletRequest request, boolean isUpdate) {
		String login = request.getParameter("login");
		String name = request.getParameter("name");
		String fstName = request.getParameter("firstname");
		String admin = request.getParameter("admin");
		String pwd = request.getParameter("pwd");
		String mail = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		if(isUpdate){
			String id = request.getParameter("id");
			return UserManager.modifyUser(id, name, fstName, login, pwd, mail, phone, admin);
		}
		else{
			return UserManager.addUser(name, fstName, login, pwd, mail, phone, admin);
		}
	}
}
