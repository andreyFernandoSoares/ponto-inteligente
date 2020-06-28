package com.andrey.ponto.inteligente.api.service;

import java.util.Optional;

import com.andrey.ponto.inteligente.api.entities.Lancamento;

public interface LancamentoService {
	
	//Page<Lancamento> buscaPorFuncionarioId(Long funcionarioId, PageRequest pageRequest );
	
	Optional<Lancamento> buscaPorId(Long id);
	
	Lancamento persistir(Lancamento lancamento);
	
	void remover(Long id);
}
