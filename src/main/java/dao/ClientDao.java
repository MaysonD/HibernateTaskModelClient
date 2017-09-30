package dao;

import model.ClientEntity;

import java.util.List;

public interface ClientDao {

    List<ClientEntity> getAllClients();

    void saveClient(ClientEntity clientEntity);

    void removeClient(ClientEntity clientEntity);

    void updateClient(ClientEntity clientEntity);
}
