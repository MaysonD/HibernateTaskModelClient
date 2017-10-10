package servlet;

import dao.ClientDaoImpl;
import model.AccountEntity;
import model.ClientEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/accounts")
public class AccountsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        HttpSession httpSession = request.getSession();
        ClientEntity clientEntity = clientDao.findById((int) (httpSession.getAttribute("clientId")));

        if (request.getParameter("updateAccount") != null) {

            AccountEntity accountEntity = clientEntity.findAccount(Integer.parseInt(request.getParameter("updateAccount")));
            accountEntity.setName(request.getParameter("accountName"));
            accountEntity.setPassword(request.getParameter("accountPassword"));
            clientDao.updateClient(clientEntity);
            request.setAttribute("accountsList", clientDao.getAllClients());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/client/showAll.jsp");
            requestDispatcher.forward(request, response);
        }

        if (request.getParameter("add") != null) {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setName(request.getParameter("accountName"));
            accountEntity.setPassword(request.getParameter("accountPassword"));
            clientEntity.addAccount(accountEntity);
            clientDao.updateClient(clientEntity);
            request.setAttribute("accountsList", clientEntity.getAccounts());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/account/showAll.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        HttpSession httpSession = request.getSession();
        ClientEntity clientEntity = clientDao.findById((int) (httpSession.getAttribute("clientId")));

        if (request.getParameter("addAccount") != null) {
            request.getRequestDispatcher("/view/account/add.jsp").forward(request, response);
        }

        if (request.getParameter("backToClients") != null) {
            httpSession.removeAttribute("clientId");
            request.setAttribute("clientsList", clientDao.getAllClients());
            request.getRequestDispatcher("/view/client/showAll.jsp").forward(request, response);
        }

        if (request.getParameter("deleteAccount") != null) {
            AccountEntity accountEntity = clientEntity.findAccount(Integer.parseInt(request.getParameter("deleteAccount")));
            clientEntity.removeAccount(accountEntity);
            clientDao.updateClient(clientEntity);
            request.setAttribute("accountsList", clientEntity.getAccounts());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/account/showAll.jsp");
            requestDispatcher.forward(request, response);
        }

        if (request.getParameter("updateAccount") != null) {
            request.setAttribute("account", clientEntity.findAccount(
                    Integer.parseInt(request.getParameter("updateAccount"))));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/account/update.jsp");
            requestDispatcher.forward(request, response);
        }

        if (request.getParameter("backHome") != null) {
            httpSession.removeAttribute("clientId");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
