import dao.ClientDaoImpl;

public class MainApp {

    public static void main(String[] a)
            throws Exception {

        ClientDaoImpl clientDao = new ClientDaoImpl();
        clientDao.getAllClients();
    }
}