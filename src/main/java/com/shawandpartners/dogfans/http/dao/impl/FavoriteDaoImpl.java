package com.shawandpartners.dogfans.http.dao.impl;

import com.shawandpartners.dogfans.http.dao.FavoriteDao;
import com.shawandpartners.dogfans.http.dao.GenericDao;
import com.shawandpartners.dogfans.http.model.Favorite;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FavoriteDaoImpl extends GenericDao<Favorite> implements FavoriteDao {

    @PersistenceContext(unitName = "dogFansPU")
    private EntityManager em;

    public FavoriteDaoImpl() {
        super(Favorite.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Favorite> findAll() {
        return super.findAll();
    }

    @Override
    public void save(String id) {
        Favorite favorite = new Favorite();
        favorite.setBreedName(id);
        super.create(favorite);
    }

    @Override
    public void remove(String id) {
        super.delete(id);
    }
}
