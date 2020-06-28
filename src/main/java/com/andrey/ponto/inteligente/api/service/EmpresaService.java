package com.andrey.ponto.inteligente.api.service;

import java.util.Optional;

import com.andrey.ponto.inteligente.api.entities.Empresa;

public interface EmpresaService {

	Optional<Empresa> buscaEmpresaPorCnpj(String cnpj);
	
	Empresa persistir(Empresa empresa);
}
