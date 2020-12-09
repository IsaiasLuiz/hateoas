package br.edu.ifsp.arq.web2.domain.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigo", "nome", "e-mail", "telefone", "cpf", "endereco", "links"})
public class ClientResource extends RepresentationModel<ClientResource> {

    @JsonProperty("codigo")
    private Long code;

    @Size(max = 255, min = 3)
    @NotBlank
    @JsonProperty("nome")
    private String name;

    @Email
    @NotBlank
    @JsonProperty("e-mail")
    private String mail;

    @Min(11)
    @NotBlank()
    @JsonProperty("telefone")
    private String phone;

    @CPF
    @NotBlank
    @JsonProperty("cpf")
    private String cpf;

    @Valid
    @NotNull
    @JsonProperty("endereco")
    private AddressResource addressResource;

}
