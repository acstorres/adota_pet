package br.com.ifma.adota.pet.model.adocao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.ifma.adota.pet.exception.BeanFacadeException;
import br.com.ifma.adota.pet.infraestrutura.DaoRepositoryException;
import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;
import br.com.ifma.adota.pet.model.cliente.Cliente;

@Stateless
public class LancamentoAdocaoSessionBeanFacade extends GenericsSessionBeanFacadeImpl<LancamentoAdocao, Long>
		implements LancamentoAdocaoSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_LancamentoAdocaoSessionBeanFacade";

	private MovimentoAdocaoSessionBeanFacadeLocal movimentoAdocaoSessionBeanFacadeLocal;

	private AnimalSessionBeanFacadeLocal animalSessionBeanFacadeLocal;

	public LancamentoAdocaoSessionBeanFacade() {
		try {
			InitialContext ic = new InitialContext();

			movimentoAdocaoSessionBeanFacadeLocal = (MovimentoAdocaoSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + MovimentoAdocaoSessionBeanFacadeLocal.JNDI);

			animalSessionBeanFacadeLocal = (AnimalSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + AnimalSessionBeanFacadeLocal.JNDI);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public LancamentoAdocao extrairLancamentoAdocaoValidos(Cliente cliente, List<Animal> animais)
			throws BeanFacadeException {
		LancamentoAdocao lancamentoAdocao = null;

		if (cliente == null)
			throw new BeanFacadeException("Cliente inválido e/ou inexistente.");

		if (animais == null || (animais != null && animais.isEmpty()))
			throw new BeanFacadeException("Não existem ou foram definidos animais para gerar adoção.");

		lancamentoAdocao = LancamentoAdocaoBuilder.umNovoLancamentoAdocaoCom(cliente)
				.definidoAutomaticamentePelaDataLancamento().constroi();

		lancamentoAdocao.criarMovimentosAdocao(animais);
		return lancamentoAdocao;
	}

	@Override
	public void processarLancamentosAdocao(LancamentoAdocao lancamentoAdocao) throws BeanFacadeException {
		if (lancamentoAdocao == null)
			throw new BeanFacadeException("Cliente inválido e/ou inexistente.");

		if (!lancamentoAdocao.isClienteValido())
			throw new BeanFacadeException("Cliente inválido e/ou inexistente.");

		if (!lancamentoAdocao.isMovimentosAdocaoValido())
			throw new BeanFacadeException("Não existem ou foram definidos animais para adoção.");

		try {
			this.include(lancamentoAdocao);

			Boolean persistidosLancamento = (lancamentoAdocao.getLancamentoAcaoId() != null);
			Boolean persistidosMovimentos = Boolean.FALSE;

			if (persistidosLancamento) {
				this.movimentoAdocaoSessionBeanFacadeLocal.salvarEmLote(lancamentoAdocao.getMovimentoAdocao());
				persistidosMovimentos = Boolean.TRUE;
			}

			if (persistidosMovimentos) {
				List<Animal> animais = new ArrayList<Animal>();

				lancamentoAdocao.getMovimentoAdocao().stream().forEach(i -> {
					animais.add(i.getAnimal());
				});
				
				if (animais != null && !animais.isEmpty()) {
					animais.stream().forEach(animal -> {
						animal.setAdotado(Boolean.TRUE);
						this.animalSessionBeanFacadeLocal.update(animal);
					});
				}
			}
		} catch (DaoRepositoryException e) {
			throw new BeanFacadeException(e.getMessage());
		}
	}

	@Override
	public void processarLancamentosAdocao(List<LancamentoAdocao> lancamentosAdocao) throws BeanFacadeException {

	}

}
