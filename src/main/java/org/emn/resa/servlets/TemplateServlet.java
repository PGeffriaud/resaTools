package org.emn.resa.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.emn.resa.entities.User;
import org.emn.resa.managers.RessourceManager;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullPath = request.getPathInfo().substring(1);
		String[] paths = fullPath.split("/");
		switch(paths[0]){
			case "ressources": 
				if(paths.length > 1){
					switch (paths[1]) {
						case "addtype": 
							RessourceManager.addType(request.getParameter("typeName"));
							break;
						case "deltype": break;
						case "updatetype": break;
						case "address": break;
						case "delress": break;
						case "updateress": break;
						default: break;
					}
				}
				request.getSession().setAttribute("listType", RessourceManager.getTypeList());
				break;
			case "reservations": break;
			case "user": break;
			case "login": break;
			case "logout": break;
			default: break;
		}
		
		String path = request.getPathInfo();
		if(path.substring(1).equals("login")){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
		}	
		else{
			request.setAttribute("page", paths[0]);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		HttpSession session = request.getSession(true);
		
		if(request.getParameter("buttonDeconnexion") != null){
			request.getSession().invalidate();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("buttonConnexion") != null){
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			System.out.println(login);
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("resaTools");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :pass");
			q.setParameter("login", login);
			q.setParameter("pass", pass);
			
			try{
				User user = (User) q.getSingleResult();
				if (login.equalsIgnoreCase(user.getLogin()) && pass.equals(user.getPassword())) {
					System.out.println("Success");
			        session.setAttribute("currentSessionUser",user); 
			        request.setAttribute("page", "accueil");
			        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			        rd.forward(request, response);
				}
			}
			catch (Exception e){
				System.out.println("Exception : " + e.getMessage());
				session.setAttribute("connectedTried",true);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/login.jsp");
				rd.forward(request, response);
			}
			finally{
				entityManagerFactory.close();
				entityManager.close();
			}
		}
	}
}
