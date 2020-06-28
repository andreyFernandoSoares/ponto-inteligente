package com.andrey.ponto.inteligente.api.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.andrey.ponto.inteligente.api.entities.Lancamento;
import com.andrey.ponto.inteligente.api.repositories.LancamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

	@MockBean
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Before
	public void setUp() {
		BDDMockito.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());
		//BDDMockito
		//.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		//.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>));
		//BDDMockito.given(this.lancamentoRepository.findById(Mockito.anyLong())).willReturn(new Lancamento());
		//BDDMockito.given(this.lancamentoRepository.findById(Mockito.anyLong())).willReturn(new Lancamento());
	}
	
	@Test
	public void testPersistirLancamento() {
		var lancamento = this.lancamentoService.persistir(new Lancamento());
		
		assertNotNull(lancamento);
	}
}
