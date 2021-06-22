package br.edu.infnet.produto.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoDto {

	private Long id;

	private BigDecimal preco;

	private Long idProduto;

	private String fornecedor;
	
	private LocalDate dataCotacao;

	private LocalDate validadeCotacao;

}
