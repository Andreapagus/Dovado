package logic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;
import javax.servlet.http.HttpSession;

import logic.controller.DAOSuperUser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	String Username;
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

		Username = request.getParameter("Uname");
		Password = request.getParameter("Password");
		System.out.println(Username + Password);
		
		
		//response.sendRedirect("login.jsp");
		if (dao.findSuperUser(Username) != null) {
			System.out.println("L'utente esiste");

			//Controllo se esiste già una sessione attiva
			HttpSession oldSession = request.getSession(false);
			if (oldSession!= null) {
				oldSession.invalidate();//la disattivo e sotto ne creo comunque una nuova
			}
			
			HttpSession currentSession = request.getSession();
			//setto attributi utili e il timer di sessione
			
			currentSession.setAttribute("User", Username);
			currentSession.setMaxInactiveInterval(300);
			
			response.sendRedirect("map.html");
		}
		else {
			System.out.println("Non esiste l'uteente");
			response.sendRedirect("login.jsp");

		}
	}

}
