package br.com.ifma.adota.pet.model.lancamento;

import java.util.List;

import javax.ejb.Local;

import br.com.ifma.adota.pet.exception.BeanFacadeException;
import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;
import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.cliente.Cliente;

@Local
public interface LancamentoSessionBeanFacadeLocal extends GenericsSessionBeanFacade<Lancamento, Integer> {

	public String JNDI = "LancamentoSessionBeanFacade!br.com.ifma.adota.pet.model.lancamento.LancamentoSessionBeanFacadeLocal";

	/**
	 * @author Daniel Santos
	 * @since 13/08/2019
	 * @category extrairLancamentoAdocaoValidos
	 * @param cliente uma instância de {@link Cliente}
	 */
	public Lancamento extrairLancamentoAdocaoValidos(Cliente cliente, List<Animal> animais) throws BeanFacadeException;

	/**
	 * @author Daniel Santos
	 * @since 13/08/2019
	 * @category processarLancamentosAdocao
	 * @param lancamentosAdocao uma lista de instâncias de {@link List} instância de
	 *                          {@link LancamentoAdocao}
	 */
	public void processarLancamentosAdocao(List<Lancamento> lancamentos) throws BeanFacadeException;

	/**
	 * @author Daniel Santos
	 * @since 13/08/2019
	 * @category processarLancamentosAdocao
	 * @param lancamentoAdocao uma instância de {@link LancamentoAdocao}
	 */
	public void processarLancamentosAdocao(Lancamento lancamento) throws BeanFacadeException;
}
