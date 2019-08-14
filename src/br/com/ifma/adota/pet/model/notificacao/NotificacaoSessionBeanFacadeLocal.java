package br.com.ifma.adota.pet.model.notificacao;

import javax.ejb.Local;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacade;

@Local
public interface NotificacaoSessionBeanFacadeLocal extends GenericsSessionBeanFacade<Notificacao, Integer> {

	public String JNDI = "NotificacaoSessionBeanFacade!br.com.ifma.adota.pet.model.notificacao.NotificacaoSessionBeanFacadeLocal";

}
