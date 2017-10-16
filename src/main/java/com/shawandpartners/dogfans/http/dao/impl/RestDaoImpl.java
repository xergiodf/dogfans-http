package com.shawandpartners.dogfans.http.dao.impl;

import com.shawandpartners.dogfans.http.dao.BaseRestDao;
import com.shawandpartners.dogfans.http.dao.RestDao;
import com.shawandpartners.dogfans.http.dto.Breed;
import com.shawandpartners.dogfans.http.util.Constants;
import com.shawandpartners.dogfans.http.util.RetrofitClient;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author xergio
 * @version 1 - 13.10.2017
 */
@Stateless
public class RestDaoImpl implements RestDao {

    private static final Logger LOG = Logger.getLogger("RestDaoImpl");

    @Override
    public List<Breed> getBreeds(String name) {
        List<String> restResult;
        List<Breed> finalResult = new ArrayList<>();

        try {
            if ("all".equals(name)) {

                restResult = getDao().getBreeds().execute().body();

                LOG.info(String.format("Cuerpo: %s", restResult.toString()));

                for (String nameResult : restResult) {
                    Breed breed = new Breed();
                    breed.setName(nameResult);
                    finalResult.add(breed);
                }
            } else {
                restResult = getDao().getImagesByName(name).execute().body();
                LOG.info(String.format("Cuerpo: %s", restResult.toString()));

                Breed breed = new Breed();
                breed.setName(name);
                breed.setImages(restResult);
                finalResult.add(breed);
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return finalResult;
    }

//  No need to make the call Asynchronous
//    private Callback<List<String>> getCallBack(final List<String> result) {
//        return new Callback<List<String>>() {
//            @Override
//            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
//
//                if (response.isSuccessful()) {
//                    result.addAll(response.body());
//                    LOG.info(String.format("Cuerpo: %s", response.body().toString()));
//                } else {
//                    LOG.info(String.format("Codigo %d - Mensaje: %s", response.code(), response.message()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<String>> call, Throwable t) {
//                LOG.log(Level.SEVERE, Constants.MSG_ERROR_DEFAULT);
//                t.printStackTrace();
//            }
//        };
//    }

    private BaseRestDao getDao() {
        return RetrofitClient.getClient(Constants.SERVER_URL).create(BaseRestDao.class);
    }
}
