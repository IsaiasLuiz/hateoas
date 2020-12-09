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
public class Client {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column
    @NotBlank
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Address address;

}

