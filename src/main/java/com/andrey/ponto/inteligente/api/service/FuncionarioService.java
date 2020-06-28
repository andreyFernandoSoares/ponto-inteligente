package com.andrey.ponto.inteligente.api.service;

import java.util.Optional;

import com.andrey.ponto.inteligente.api.entities.Funcionario;

public interface FuncionarioService {

	Funcionario persistir(Funcionario funcionario);
	
	Optional<Funcionario> buscaPorCpf(String cpf);
	
	Optional<Funcionario> buscaPorEmail(String email);
	
	Optional<Funcionario> buscarPorId(Long id);
	
}
