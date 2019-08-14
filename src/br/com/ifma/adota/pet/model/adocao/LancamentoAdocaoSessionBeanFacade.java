package br.com.ifma.adota.pet.model.adocao;

import java.util.List;

import javax.ejb.Stateless;

import br.com.ifma.adota.pet.exception.BeanFacadeException;
import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;
import br.com.ifma.adota.pet.model.cliente.Cliente;

@Stateless
public class LancamentoAdocaoSessionBeanFacade extends GenericsSessionBeanFacadeImpl<LancamentoAdocao, Long>
		implements LancamentoAdocaoSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_LancamentoAdocaoSessionBeanFacade";

	@Override
	public LancamentoAdocao extrairLancamentoAdocaoValidos(Cliente cliente) throws BeanFacadeException {
		LancamentoAdocao lancamentoAdocao = null;

		return lancamentoAdocao;
	}

	@Override
	public void processarLancamentosAdocao(LancamentoAdocao lancamentoAdocao) throws BeanFacadeException {

	}

	@Override
	public void processarLancamentosAdocao(List<LancamentoAdocao> lancamentosAdocao) throws BeanFacadeException {

	}

}
