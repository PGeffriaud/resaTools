package org.emn.resa.servlets;

import java.io.IOException;
import java.util.List;

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

/**
 * Servlet implementation class TemplateServlet
 */
@WebServlet("/page/*")
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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("resaTools");
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT t.name FROM Type t");
		List<String> list = q.getResultList();
		request.getSession().setAttribute("listType", list);
		entityManagerFactory.close();
		em.close();
		
		String path = request.getPathInfo();
		request.setAttribute("page", path.substring(1));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if(request.getParameter("buttonDeconnexion") != null){
			request.getSession().invalidate();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("buttonConnexion") != null){
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
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
				}
			}
			catch (Exception e){
				System.out.println("Exception : " + e.getMessage());
			}
			finally{
				entityManagerFactory.close();
				entityManager.close();
				session.setAttribute("connectedTried",true);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		}
	}
}
