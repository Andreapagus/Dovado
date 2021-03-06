package logic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.model.DAOSuperUser;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String Username;
	String Password;
	String Email;
	DAOSuperUser dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Non implementato   il get. Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao = DAOSuperUser.getInstance();
		Email = request.getParameter("Uemail");
		Username = request.getParameter("Uname");
		Password = request.getParameter("Password");
		//Questo controllo gi� viene fatto, andrebbe solo gestito l'errore 
		if (dao.findSuperUser(Email) != null) {
			System.out.println("L'utente esiste");
			request.setAttribute("exist","Esiste gi� un account associato a questa email");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			
		}
		else {
			System.out.println("Non esiste l'utente");
			dao.addUserToJSON(Email, Username, 0, Password);
			response.sendRedirect("login.jsp");

		}
	}

}
