package com.shawandpartners.dogfans.http.dao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 *
 * @author xergio
 * @version 1 - 13.10.2017
 */
public interface BaseRestDao {

    @GET("breeds")
    Call<List<String>> getBreeds();

    @GET("{name}/images")
    Call<List<String>> getImagesByName(@Path("name") String name);

}
