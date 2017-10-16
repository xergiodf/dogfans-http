package com.shawandpartners.dogfans.http.util.converter;

import com.shawandpartners.dogfans.http.dto.Breed;
import com.shawandpartners.dogfans.http.dto.BreedWrapper;
import com.shawandpartners.dogfans.http.model.Favorite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xergio
 * @version 1 - 14.10.2017
 */
public class BreedConverter {

    private static BreedConverter INSTANCE = null;

    private BreedConverter() {}

    public static final BreedConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BreedConverter();
        }

        return INSTANCE;
    }

    public List<BreedWrapper> getBreedWrappers(List<Breed> breeds, List<Favorite> favorites) {
        List<BreedWrapper> breedWrappers = new ArrayList<>();

        for (Breed breed : breeds) {
            breedWrappers.add(getBreedWrapper(breed, favorites));
        }

        return breedWrappers;
    }

    private BreedWrapper getBreedWrapper(Breed breed, List<Favorite> favorites) {
        BreedWrapper breedWrapper = new BreedWrapper();
        breedWrapper.setBreed(breed);
        breedWrapper.setFavorite(Boolean.FALSE);

        favorites.forEach(f -> {
            if (f.getBreedName().equals(breed.getName())) {
                breedWrapper.setFavorite(Boolean.TRUE);
            }
        });

        return breedWrapper;
    }
}
