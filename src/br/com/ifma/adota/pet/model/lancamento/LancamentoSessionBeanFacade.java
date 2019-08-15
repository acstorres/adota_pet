package br.com.ifma.adota.pet.model.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.ifma.adota.pet.exception.BeanFacadeException;
import br.com.ifma.adota.pet.infraestrutura.DaoRepositoryException;
import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;
import br.com.ifma.adota.pet.model.adocao.AdocaoSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.animal.AnimalSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.cliente.Cliente;

@Stateless
public class LancamentoSessionBeanFacade extends GenericsSessionBeanFacadeImpl<Lancamento, Integer>
		implements LancamentoSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_LancamentoSessionBeanFacade";

	private AdocaoSessionBeanFacadeLocal adocaoSessionBeanFacadeLocal;

	private AnimalSessionBeanFacadeLocal animalSessionBeanFacadeLocal;

	public LancamentoSessionBeanFacade() {
		try {
			InitialContext ic = new InitialContext();

			adocaoSessionBeanFacadeLocal = (AdocaoSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + AdocaoSessionBeanFacadeLocal.JNDI);

			animalSessionBeanFacadeLocal = (AnimalSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + AnimalSessionBeanFacadeLocal.JNDI);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Lancamento extrairLancamentoAdocaoValidos(Cliente cliente, List<Animal> animais) throws BeanFacadeException {
		Lancamento lancamento = null;

		if (cliente == null)
			throw new BeanFacadeException("Cliente inválido e/ou inexistente.");

		if (animais == null || (animais != null && animais.isEmpty()))
			throw new BeanFacadeException("Não existem ou foram definidos animais para gerar adoção.");

		lancamento = LancamentoBuilder.umNovoLancamentoCom(cliente).definidoAutomaticamentePelaDataLancamento()
				.constroi();

		lancamento.criarMovimentosAdocao(animais);
		return lancamento;
	}

	@Override
	public void processarLancamentosAdocao(Lancamento lancamentoAdocao) throws BeanFacadeException {
		if (lancamentoAdocao == null)
			throw new BeanFacadeException("Cliente inválido e/ou inexistente.");

		if (!lancamentoAdocao.isClienteValido())
			throw new BeanFacadeException("Cliente inválido e/ou inexistente.");

		if (!lancamentoAdocao.isMovimentosAdocaoValido())
			throw new BeanFacadeException("Não existem ou foram definidos animais para adoção.");

		try {
			this.include(lancamentoAdocao);

			Boolean persistidosLancamento = (lancamentoAdocao.getLancamentoId() != null);
			Boolean persistidosMovimentos = Boolean.FALSE;

			if (persistidosLancamento) {
				this.adocaoSessionBeanFacadeLocal.salvarEmLote(lancamentoAdocao.getAdocao());
				persistidosMovimentos = Boolean.TRUE;
			}

			if (persistidosMovimentos) {
				List<Animal> animais = new ArrayList<Animal>();

				lancamentoAdocao.getAdocao().stream().forEach(i -> {
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
	public void processarLancamentosAdocao(List<Lancamento> lancamentos) throws BeanFacadeException {

	}

}
