package servlet;

import dao.ClientDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("addClient") != null) {

            request.getRequestDispatcher("/view/client/add.jsp").forward(request, response);
        }

        if (request.getParameter("showClients") != null) {
            ClientDaoImpl clientDao = new ClientDaoImpl();
            request.setAttribute("clientsList", clientDao.getAllClients());
            request.getRequestDispatcher("/view/client/showAll.jsp").forward(request, response);
        }
    }
}