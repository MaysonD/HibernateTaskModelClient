package servlet;

import dao.ClientDaoImpl;
import model.ClientEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/clients")
public class ClientsServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        config.getServletContext().setAttribute("clientsList", clientDao.getAllClients());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientDaoImpl clientDao = new ClientDaoImpl();

        if (request.getParameter("updateClient") != null) {
            System.out.println("post " + request.getParameter("updateClient"));
            ClientEntity clientEntity = clientDao.findById(
                    Integer.parseInt(request.getParameter("updateClient")));
            clientEntity.setName(request.getParameter("clientName"));
            clientEntity.setPassword(request.getParameter("clientPassword"));
            clientDao.updateClient(clientEntity);
            request.setAttribute("clientsList", clientDao.getAllClients());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/client/showAll.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientDaoImpl clientDao = new ClientDaoImpl();

        if (request.getParameter("addClient") != null) {
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setName(request.getParameter("clientName"));
            clientEntity.setPassword(request.getParameter("clientPassword"));
            clientDao.saveClient(clientEntity);
            request.setAttribute("clientsList", clientDao.getAllClients());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/client/showAll.jsp");
            requestDispatcher.forward(request, response);
        }

        if (request.getParameter("showClients") != null) {
            request.setAttribute("clientsList", clientDao.getAllClients());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/client/showAll.jsp");
            requestDispatcher.forward(request, response);
        }

        if (request.getParameter("deleteClient") != null) {
            clientDao.removeClient(Integer.parseInt(request.getParameter("deleteClient")));
            request.setAttribute("clientsList", clientDao.getAllClients());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/client/showAll.jsp");
            requestDispatcher.forward(request, response);
        }

        if (request.getParameter("updateClient") != null) {
            request.setAttribute("client", clientDao.findById(
                    Integer.parseInt(request.getParameter("updateClient"))));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/client/update.jsp");
            requestDispatcher.forward(request, response);
        }

        if (request.getParameter("backHome") != null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        if (request.getParameter("showAccounts") != null) {
            ClientEntity clientEntity = clientDao.findById(
                    Integer.parseInt(request.getParameter("showAccounts")));
            request.setAttribute("accountsList", clientEntity.getAccounts());
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("clientId", clientEntity.getId());
            HttpSession session = request.getSession();
            session.setAttribute("client", clientEntity);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/account/showAll.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}