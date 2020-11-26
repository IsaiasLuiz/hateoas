package br.edu.ifsp.arq.web2.week_3.domain.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigo", "nome", "e-mail", "telefone", "links"})
public class ClientResource extends RepresentationModel<ClientResource> {

    @JsonProperty("codigo")
    private Long code;

    @NotBlank
    @JsonProperty("nome")
    private String name;

    @NotBlank
    @JsonProperty("e-mail")
    private String mail;

    @NotBlank
    @JsonProperty("telefone")
    private String phone;

}
