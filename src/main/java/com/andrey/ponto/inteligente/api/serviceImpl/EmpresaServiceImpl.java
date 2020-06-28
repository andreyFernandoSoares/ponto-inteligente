package com.andrey.ponto.inteligente.api.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrey.ponto.inteligente.api.entities.Empresa;
import com.andrey.ponto.inteligente.api.repositories.EmpresaRepository;
import com.andrey.ponto.inteligente.api.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	//private static final Logger log = LoggerFactory.getLogger(EmpresaService.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscaEmpresaPorCnpj(String cnpj) {
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		return this.empresaRepository.save(empresa);
	}
	
}
