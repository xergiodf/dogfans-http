package com.shawandpartners.dogfans.http.dao;

import com.shawandpartners.dogfans.http.model.Favorite;

import java.util.List;

/**
 *
 * @author xergio
 * @version 1 - 14.10.2017
 */
public interface FavoriteDao {

    public List<Favorite> findAll();

    public void save(String id);

    public void remove(String id);
}
