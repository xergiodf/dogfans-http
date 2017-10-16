package com.shawandpartners.dogfans.http.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author xergio
 * @version 1 - 14.10.2017
 */
public class BreedWrapper {

    @Getter
    @Setter
    private Breed breed;

    @Getter
    @Setter
    private Boolean favorite;
}
