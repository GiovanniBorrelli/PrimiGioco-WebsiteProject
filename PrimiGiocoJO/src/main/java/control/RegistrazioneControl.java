package control;

import java.io.IOException;
import java.sql.SQLException;

import model.bean.UserBean;
import model.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegistrazioneControl")
public class RegistrazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static final UserDAO modelUser = new UserDAO();
   
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UserBean user = new UserBean();
			user.setNome(request.getParameter("nm"));
			user.setCognome(request.getParameter("cgm"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("psw"));
			modelUser.doSave(user);
			user = modelUser.doRetrieveUtente(request.getParameter("email"), request.getParameter("psw"));
			request.getSession().setAttribute("Utente", user);
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo");
		dispatcher.forward(request, response);	
	}
}
