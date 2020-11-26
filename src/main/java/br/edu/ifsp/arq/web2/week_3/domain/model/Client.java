package br.edu.ifsp.arq.web2.week_3.domain.model;

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
public class Client {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String phone;

    @Column
    @NotBlank
    private String mail;

}

