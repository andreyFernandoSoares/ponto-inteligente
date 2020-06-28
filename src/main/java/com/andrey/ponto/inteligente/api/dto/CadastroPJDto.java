package com.andrey.ponto.inteligente.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroPJDto {

	private Long id;
	
	@NotEmpty(message = "nome não pode ser vazio")
	@Length(min = 3, max = 200, message = "nome deve conter entre 3 e 200 caracteres")
	private String nome;
	
	@NotEmpty(message = "email não pode ser vazio")
	@Length(min = 5, max = 200, message = "email deve conter entre 5 e 200 caracteres")
	@Email(message = "email inválido")
	private String email;
	
	@NotEmpty(message = "senha não pode ser vazio")
	private String senha;

	@NotEmpty(message = "cpf não pode ser vazio")
	@CPF(message = "cpf inválido")
	private String cpf;
	
	@NotEmpty(message = "razão não pode ser vazio")
	@Length(min = 5, max = 200, message = "razão deve conter entre 5 e 200 caracteres")
	private String razaoSocial;
	
	@NotEmpty(message = "cnpj não pode ser vazio")
	@CNPJ(message = "cnpj inválido")
	private String cnpj;
	
	public CadastroPJDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "CadastroPJDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + "]";
	}
	
}
