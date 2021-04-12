package logic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;

import logic.model.DAOSuperUser;

import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	String Email;
	String Password;
	DAOSuperUser dao;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Non implementato il get ma il POST");	
		response.getWriter().append("Non implementato  il get. Served at: ").append(request.getContextPath());
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao = DAOSuperUser.getInstance();

		Email = request.getParameter("Uemail");
		Password = request.getParameter("Password");
		System.out.println(Email + Password);
		
		
		//response.sendRedirect("login.jsp");
		if (dao.findSuperUser(Email,Password) != null) {
			System.out.println("L'utente esiste");

			//Controllo se esiste già una sessione attiva
			HttpSession oldSession = request.getSession(false);
			if (oldSession!= null) {
				oldSession.invalidate();//la disattivo e sotto ne creo comunque una nuova
			}
			
			HttpSession currentSession = request.getSession();
			//setto attributi utili e il timer di sessione
			
			currentSession.setAttribute("User", Email);
			currentSession.setMaxInactiveInterval(15);
			
			response.sendRedirect("test.jsp");
		}
		else {
			System.out.println("Non esiste l'utente");
			request.setAttribute("wrongLogin","Email o password errati");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
