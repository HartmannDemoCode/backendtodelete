package dk.cphbusiness.daos;

import dk.cphbusiness.persistence.model.IJPAEntity;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * This is an interface for making a DAO (Data Access Object) that can be used to perform CRUD operations on any entity.
 *
 * @param <T>
 */
interface IDAO<T extends IJPAEntity> {


    void setEntityManagerFactory(EntityManagerFactory emf);

    EntityManagerFactory getEntityManagerFactory();

    T findById(Object id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void delete(T t);

    void truncate();

    void close();
}