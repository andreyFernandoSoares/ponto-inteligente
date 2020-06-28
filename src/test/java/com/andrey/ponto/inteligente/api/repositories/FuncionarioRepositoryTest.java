package com.andrey.ponto.inteligente.api.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andrey.ponto.inteligente.api.util.PasswordUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.andrey.ponto.inteligente.api.entities.Empresa;
import com.andrey.ponto.inteligente.api.entities.Funcionario;
import com.andrey.ponto.inteligente.api.enums.PerfilEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String CPF = "00336452055";
	private static final String EMAIL = "andrey@weecode.com";
	
	@Before
	public void setUp() {
		var empresa = this.empresaRepository.save(obterDadosEmpresa());
		this.funcionarioRepository.save(obterDadosFuncionario(empresa));
	}
	
	@After
	public void tearDown() {
		funcionarioRepository.deleteAll();
	}

	@Test
	public void TestBuscarFuncionarioPorEmail() {
		var funcionario = this.funcionarioRepository.findByEmail(EMAIL);
		
		assertEquals(EMAIL, funcionario.getEmail());
	}
	
	@Test
	public void TestBuscarFuncionarioPorCpf() {
		var funcionario = this.funcionarioRepository.findByCpf(CPF);
		
		assertEquals(CPF, funcionario.getCpf());
	}
	
	@Test
	public void TestBuscarFuncionarioPorCpfEEmail() {
		var funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void TestBuscarFuncionarioPorCpfEEmailInvalido() {
		var funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, "andrey@invalido.com");
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void TestBuscarFuncionarioPorCpfInvalidoEEmail() {
		var funcionario = this.funcionarioRepository.findByCpfOrEmail("123", EMAIL);
		
		assertNotNull(funcionario);
	}
	
	private Funcionario obterDadosFuncionario(Empresa empresa) {
		var funcionario = new Funcionario();
		funcionario.setNome("fulano de tal");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setEmpresa(empresa);
		
		return funcionario;
		
	}
	
	private Empresa obterDadosEmpresa() {
		var empresa = new Empresa();
		empresa.setRazaoSocia("empresa de teste");
		empresa.setCnpj("000100010001111");
		
		return empresa;
	}

}
