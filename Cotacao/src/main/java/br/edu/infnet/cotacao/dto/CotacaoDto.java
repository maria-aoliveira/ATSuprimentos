package br.edu.infnet.cotacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoDto {

	private Long id;

	@NotNull(message =  "Preco nao pode ser nulo")
	private BigDecimal preco;
	
	
	@NotNull(message =  "Id do produto Foto nao pode ser nulo")
	private Long idProduto;

	@NotNull(message =  "Fornecedor nao pode ser nulo")
	private String fornecedor;
	
	private String produto;
	
	private LocalDate dataCotacao;

	@FutureOrPresent(message = "Confira a data de validade, ela nao pode ser anterior a hoje")
	@NotNull(message =  "Data da validade nao pode ser nulo")
	private LocalDate validadeCotacao;
}
