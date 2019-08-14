package br.com.ifma.adota.pet.model.notificacao;

import javax.ejb.Stateless;

import br.com.ifma.adota.pet.infraestrutura.GenericsSessionBeanFacadeImpl;

@Stateless
public class NotificacaoSessionBeanFacade extends GenericsSessionBeanFacadeImpl<Notificacao, Integer>
		implements NotificacaoSessionBeanFacadeLocal {

	public static final String NAME = "adota_pet_NotificacaoSessionBeanFacade";
}
