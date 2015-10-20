package org.emn.resa.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.resa.entities.Type;

/**
 * Servlet implementation class CreateTypeServlet
 */
@WebServlet("/addtype")
public class CreateTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTypeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomType = request.getParameter("typeName");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("resaTools");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Type t = new Type();
		t.setName(nomType);
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		request.setAttribute("query", true);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

}
