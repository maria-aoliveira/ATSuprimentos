package br.edu.infnet.cotacao.persistence.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cotacao", schema = "cotacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cotacao {

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco")
	private BigDecimal preco;
	
	@Column(name = "idProduto")
	private Long idProduto;
	
	@Column(name = "Nome_Fornecedor")
	private String fornecedor;
	
	@Column(name = "Nome_Produto")
	private String produto;
	
	@Column(name = "data_cotacao", updatable = false)
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCotacao;
	
	@Column(name = "validade_cotacao")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate validadeCotacao;
}
