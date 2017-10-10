package dao;

import model.ClientEntity;

import java.util.List;

public interface ClientDao {

    List<ClientEntity> getAllClients();

    void saveClient(ClientEntity clientEntity);

    ClientEntity findById(int id);

    void removeClient(int id);

    void updateClient(ClientEntity clientEntity);
}
