package com.andrey.ponto.inteligente.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import com.andrey.ponto.inteligente.api.enums.TipoEnum;
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
import com.andrey.ponto.inteligente.api.entities.Lancamento;
import com.andrey.ponto.inteligente.api.enums.PerfilEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private static final String CPF = "00336452055";
	private static final String EMAIL = "andrey@weecode.com";

	private Long funcionarioId;
	
	@Before
	public void setUp() {
		var empresa = this.empresaRepository.save(obterDadosEmpresa());
		
		var funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
		this.funcionarioId = funcionario.getId();
		
		this.lancamentoRepository.save(obterDadosLancamento(funcionario));
		this.lancamentoRepository.save(obterDadosLancamento(funcionario));
	}
	
	@After
	public void tearDown() {
		this.empresaRepository.deleteAll();;
	}
	
	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		List<Lancamento> lancamento = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		
		assertEquals(2, lancamento.size());
	}
	
//	@Test
//	public void testBuscarLancamentosPorFuncionarioIdPaginado() {
//		PageRequest page = new PageRequest(int 0, int 10);
//		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);
//		
//		assertEquals(2, lancamentos.getTotalElements());
//	}
	
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
	
	private Lancamento obterDadosLancamento(Funcionario funcionario) {
		var lancamento = new Lancamento();
		
		lancamento.setData(new Date());
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setDescricao("lancamento");
		lancamento.setFuncionario(funcionario);
		
		return lancamento;
		
	}
}
