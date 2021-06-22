package br.edu.infnet.produto.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.edu.infnet.produto.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoDto {
	
	private Long id;
	
	@NotNull(message =  "Nome nao pode ser nulo")
	private String nome;

	@URL
	@NotNull(message =  "Url da Foto nao pode ser nulo")
	private String foto;

	@NotNull(message =  " nao pode ser nulo")
	private Categoria categoria;

	private List<CotacaoDto> cotacoes;
}
