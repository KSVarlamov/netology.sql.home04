package ru.netology.home04.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "PERSONS", schema = "netology")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private PersonPK pk;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city_of_living")
    private String city;

}

