package br.edu.infnet.cotacao.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.cotacao.persistence.model.Cotacao;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long>{
	
	List<Cotacao> findByValidadeCotacaoGreaterThanEqualOrderById(LocalDate validadeCotacao);
	
	List<Cotacao> findByValidadeCotacaoLessThanOrderById(LocalDate validadeCotacao);
	
	List<Cotacao> findByIdProdutoAndValidadeCotacaoGreaterThanEqualOrderById(Long idProduto, LocalDate data);
}
