package control;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import model.bean.ProdottoBean;
import model.dao.ProdottoDAO;

@WebServlet("/InsertAdminControl")
public class InsertAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ProdottoDAO modelProd = new ProdottoDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoBean bean = new ProdottoBean();
		bean.setTipologia(request.getParameter("tipologia"));
		bean.setNome(request.getParameter("nomeProd"));
		bean.setDescrizione(request.getParameter("descrizione"));
		bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
		bean.setQuantita(Integer.parseInt(request.getParameter("quantita")));
		bean.setIva(Double.parseDouble(request.getParameter("iva")));
		bean.setPath("./Prodotti/"+request.getParameter("image"));
		

		try {
			modelProd.doSave(bean);
		} catch (SQLException e) {
			System.out.println("Errore Admin Control:"+e.getMessage());
			e.printStackTrace();
		}
		
		

		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
