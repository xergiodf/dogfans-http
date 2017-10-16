package com.shawandpartners.dogfans.http.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
public class Breed {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<String> images;
}
