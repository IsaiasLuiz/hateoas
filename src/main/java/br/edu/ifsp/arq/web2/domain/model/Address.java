package br.edu.ifsp.arq.web2.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String street;

    @Column
    @NotBlank
    private String number;

    @Column
    @NotBlank
    private String complement;

    @Column
    @NotBlank
    private String district;

    @Column
    @NotBlank
    private String city;

    @Column
    @NotBlank
    private String state;

    @Column
    @NotBlank
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Client client;

}

