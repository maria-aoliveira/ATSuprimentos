package br.edu.infnet.cotacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoUpdateDto {

	@NotNull(message = "Indique o id da cotacao que voce quer alterar")
	private Long id;

	@NotNull(message =  "Preco nao pode ser nulo")
	private BigDecimal preco;
	
	
	@NotNull(message =  "Id do produto nao pode ser nulo")
	private Long idProduto;

	@NotNull(message =  "Fornecedor nao pode ser nulo")
	private String fornecedor;
	
	@NotNull(message =  "Produto nao pode ser nulo")
	private String produto;
	
	@PastOrPresent(message = "Atencao, voce nao pode alterar a data ja cadastrada")
	@NotNull(message = "Data do lancamento nao pode ser nula")
	private LocalDate dataCotacao;

	@FutureOrPresent(message = "Confira a data de validade, ela nao pode ser anterior a hoje")
	@NotNull(message =  "Data da validade nao pode ser nulo")
	private LocalDate validadeCotacao;
}
