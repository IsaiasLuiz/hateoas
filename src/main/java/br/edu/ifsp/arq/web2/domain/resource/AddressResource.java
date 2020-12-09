package br.edu.ifsp.arq.web2.domain.resource;

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
@JsonPropertyOrder({"logradouro", "numero", "complemento", "bairro", "cidade", "estado",  "cep", "links"})
public class AddressResource extends RepresentationModel<AddressResource> {

    @NotBlank
    @JsonProperty("logradouro")
    private String street;

    @NotBlank
    @JsonProperty("numero")
    private String number;

    @NotBlank
    @JsonProperty("complemento")
    private String complement;

    @NotBlank
    @JsonProperty("bairro")
    private String district;

    @NotBlank
    @JsonProperty(value = "cidade")
    private String city;

    @NotBlank
    @JsonProperty("estado")
    private String state;

    @NotBlank
    @JsonProperty("cep")
    private String zipCode;

}
