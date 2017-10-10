package dao;

import model.ClientEntity;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    public List<ClientEntity> getAllClients() {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<ClientEntity> accounts = entityManager.createNativeQuery("SELECT * from clients", ClientEntity.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return accounts;
    }

    public void saveClient(ClientEntity clientEntity) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(clientEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public ClientEntity findById(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        ClientEntity entity = entityManager.find(ClientEntity.class, id);
        entityManager.close();
        return entity;
    }

    public void removeClient(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        ClientEntity entityForRemove = entityManager.find(ClientEntity.class, id);
        entityManager.remove(entityForRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateClient(ClientEntity clientEntity) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(clientEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
