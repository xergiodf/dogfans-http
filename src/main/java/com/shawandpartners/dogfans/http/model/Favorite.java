package com.shawandpartners.dogfans.http.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author xergio
 * @version 1 - 14.10.2017
 */
@Entity
@Table(name = "favorites")
@XmlRootElement
@ToString
@EqualsAndHashCode
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "breed_name")
    @Getter
    @Setter
    private String breedName;
}
