package br.edu.infnet.produto.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.infnet.produto.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "PRODUTO", schema = "produto")
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "FOTO")
	private String foto;
	
	@Column(name = "CATEGORIA")
	@Enumerated(value= EnumType.STRING)
	private Categoria categoria;
}
