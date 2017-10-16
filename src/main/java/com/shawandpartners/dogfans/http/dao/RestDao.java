package com.shawandpartners.dogfans.http.dao;

import com.shawandpartners.dogfans.http.dto.Breed;

import java.io.IOException;
import java.util.List;

public interface RestDao {

    public List<Breed> getBreeds(String name) throws IOException;
}
