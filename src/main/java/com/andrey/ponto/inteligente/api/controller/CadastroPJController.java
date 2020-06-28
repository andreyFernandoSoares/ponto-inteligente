package com.andrey.ponto.inteligente.api.controller;

import javax.validation.Valid;

import com.andrey.ponto.inteligente.api.dto.CadastroPJDto;
import com.andrey.ponto.inteligente.api.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrey.ponto.inteligente.api.entities.Empresa;
import com.andrey.ponto.inteligente.api.entities.Funcionario;
import com.andrey.ponto.inteligente.api.enums.PerfilEnum;
import com.andrey.ponto.inteligente.api.response.Response;
import com.andrey.ponto.inteligente.api.service.EmpresaService;
import com.andrey.ponto.inteligente.api.service.FuncionarioService;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJController {

	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	//private CadastroPJDto cadastroPJDto;
	
	public CadastroPJController() {
	}
	
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto,
                                                             BindingResult result){
		
		Response<CadastroPJDto> response = new Response<CadastroPJDto>();
		
		validarDadosExistentes(cadastroPJDto, result);
		var empresa = this.converterDtoParaEmpresa(cadastroPJDto);
		var funcionario = this.converterDtoParaFuncionario(cadastroPJDto, result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.persistir(funcionario);
		
		response.setData(this.converterCadastroPJDto(funcionario));
		return ResponseEntity.ok(response);
	}
	
	private Empresa converterDtoParaEmpresa(CadastroPJDto cadastroPJDto) {
		var empresa = new Empresa();
		
		empresa.setCnpj(cadastroPJDto.getCnpj());
		empresa.setRazaoSocia(cadastroPJDto.getRazaoSocial());
		
		return empresa;
	}
	
	private Funcionario converterDtoParaFuncionario(CadastroPJDto cadastroPJDto, BindingResult result) {
		var funcionario = new Funcionario();
		
		funcionario.setNome(cadastroPJDto.getNome());
		funcionario.setEmail(cadastroPJDto.getEmail());
		funcionario.setCpf(cadastroPJDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(cadastroPJDto.getSenha()));
		
		return funcionario;
	}
	
	private CadastroPJDto converterCadastroPJDto(Funcionario funcionario) {
		var cadastroPJDto = new CadastroPJDto();
		
		cadastroPJDto.setId(funcionario.getId());
		cadastroPJDto.setNome(funcionario.getNome());
		cadastroPJDto.setEmail(funcionario.getEmail());
		cadastroPJDto.setCpf(funcionario.getCpf());
		cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocia());
		cadastroPJDto.setCnpj(funcionario.getEmpresa().getRazaoSocia());
		
		return cadastroPJDto;
	}
	
	private void validarDadosExistentes(CadastroPJDto cadastroPJDto, BindingResult result) {
		
		this.empresaService.buscaEmpresaPorCnpj(cadastroPJDto.getCnpj())
			.ifPresent(emp -> result.addError(new ObjectError("empresa", "empresa já existente")));
		
		this.funcionarioService.buscaPorCpf(cadastroPJDto.getCpf())
			.ifPresent(func -> result.addError(new ObjectError("funcionario", "cpf já existe")));
		
		this.funcionarioService.buscaPorEmail(cadastroPJDto.getEmail())
			.ifPresent(func -> result.addError(new ObjectError("funcionario", "email já existente")));
	}
}
