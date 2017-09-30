package servlet;

import dao.ClientDaoImpl;
import model.ClientEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientDaoImpl clientDao = new ClientDaoImpl();

        if(request.getParameter("add")!=null){//при нажатии на кнопку add
            System.out.println("WORK");
            ClientEntity clientEntity = new ClientEntity();//создаём экземпляр класса модели базы данных
            clientEntity.setName(request.getParameter("name"));
            clientEntity.setPassword(request.getParameter("password"));
            clientDao.saveClient(clientEntity);//сохраняем в базу данных полученный объект
            request.setAttribute("clientsList",clientDao.getAllClients());//создаём аттрибут который взял в себя всё что есть в базе данных
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("list.jsp");//перебрасываемся на list.jsp
            requestDispatcher.forward(request,response);
        }

        if(request.getParameter("showAll")!=null){//при нажатии на кнопку showALL
            System.out.println("WORK");
            request.setAttribute("clientsList",clientDao.getAllClients());//создаём аттрибут который взял в себя всё что есть в базе данных
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("list.jsp");//перебрасываемся на list.jsp
            requestDispatcher.forward(request,response);
        }
    }
}