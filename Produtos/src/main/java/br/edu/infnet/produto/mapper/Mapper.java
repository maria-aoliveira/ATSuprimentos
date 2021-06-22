package br.edu.infnet.produto.mapper;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.produto.dto.CotacaoDto;
import br.edu.infnet.produto.dto.ProdutoDto;
import br.edu.infnet.produto.persistence.model.Produto;

public class Mapper {

	public Produto fromModel(ProdutoDto model){
		var produto = new Produto();
		produto.setId(model.getId());
		produto.setNome(model.getNome());
		produto.setFoto(model.getFoto());
		produto.setCategoria(model.getCategoria());
		
		return produto;
	}
	
	public ProdutoDto fromEntity(Produto entity){
		var model = new ProdutoDto();
		model.setId(entity.getId());
		model.setNome(entity.getNome());
		model.setFoto(entity.getFoto());
		model.setCategoria(entity.getCategoria());
		
		return model;
	}
	
	public ProdutoDto fromEntity(Produto entity, List<CotacaoDto> cotacoes){
		var model = new ProdutoDto();
		model.setId(entity.getId());
		model.setNome(entity.getNome());
		model.setFoto(entity.getFoto());
		model.setCategoria(entity.getCategoria());
		model.setCotacoes(cotacoes);
		
		return model;
	}
	
	public List<ProdutoDto> fromEntity(List<Produto> entityList){
		List<ProdutoDto> list = new ArrayList<>();
		
		for(Produto entity : entityList) {
			var model = new ProdutoDto();
			model.setId(entity.getId());
			model.setNome(entity.getNome());
			model.setFoto(entity.getFoto());
			model.setCategoria(entity.getCategoria());
			
			list.add(model);
		}
		return list;
	}
	
	public List<ProdutoDto> fromEntity(List<Produto> entityList, List<CotacaoDto> cotacoes){
		List<ProdutoDto> list = new ArrayList<>();
		
		for(Produto entity : entityList) {
			var model = new ProdutoDto();
			model.setId(entity.getId());
			model.setNome(entity.getNome());
			model.setFoto(entity.getFoto());
			model.setCategoria(entity.getCategoria());
			model.setCotacoes(cotacoes);
			
			list.add(model);
		}
		return list;
	}
}
