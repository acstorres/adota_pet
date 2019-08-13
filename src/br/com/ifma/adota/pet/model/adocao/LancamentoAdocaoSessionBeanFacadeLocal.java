package br.com.ifma.adota.pet.model.adocao;

import java.util.List;

import javax.ejb.Local;

import br.com.ifma.adota.pet.exception.BeanFacadeException;
import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;
import br.com.ifma.adota.pet.model.cliente.Cliente;

@Local
public interface LancamentoAdocaoSessionBeanFacadeLocal extends GenericsSessionBeanFacade<LancamentoAdocao, Long> {

	public String JNDI = "LancamentoAdocaoSessionBeanFacade!br.com.ifma.adota.pet.model.adocao.LancamentoAdocaoSessionBeanFacadeLocal";

	/**
	 * @author Daniel Santos
	 * @since 13/08/2019
	 * @category extrairLancamentoAdocaoValidos
	 * @param cliente uma instância de {@link Cliente}
	 */
	public LancamentoAdocao extrairLancamentoAdocaoValidos(Cliente cliente) throws BeanFacadeException;

	/**
	 * @author Daniel Santos
	 * @since 13/08/2019
	 * @category processarLancamentosAdocao
	 * @param lancamentosAdocao uma lista de instâncias de {@link List} instância de
	 *                          {@link LancamentoAdocao}
	 */
	public void processarLancamentosAdocao(List<LancamentoAdocao> lancamentosAdocao) throws BeanFacadeException;

	/**
	 * @author Daniel Santos
	 * @since 13/08/2019
	 * @category processarLancamentosAdocao
	 * @param lancamentoAdocao uma instância de {@link LancamentoAdocao}
	 */
	public void processarLancamentosAdocao(LancamentoAdocao lancamentoAdocao) throws BeanFacadeException;

}
