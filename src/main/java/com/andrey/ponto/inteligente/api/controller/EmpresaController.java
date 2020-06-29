package com.andrey.ponto.inteligente.api.controller;

import java.util.Optional;

import com.andrey.ponto.inteligente.api.dto.EmpresaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrey.ponto.inteligente.api.entities.Empresa;
import com.andrey.ponto.inteligente.api.response.Response;
import com.andrey.ponto.inteligente.api.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;
	
	public EmpresaController() {
	}

	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj){
		
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.buscaEmpresaPorCnpj(cnpj);
		
		if(!empresa.isPresent()) {
			response.getErrors().add("empresa não encontrada " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok().body(response);
	}

	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		var empresaDto = new EmpresaDto();
		
		empresaDto.setId(empresa.getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocia());
		
		return empresaDto;
	}
}
