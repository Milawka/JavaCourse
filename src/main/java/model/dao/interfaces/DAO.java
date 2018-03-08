package model.dao.interfaces;

import java.util.List;

public interface DAO<E, PK> {
    E getById(PK id);

    List<E> getAll();

}
