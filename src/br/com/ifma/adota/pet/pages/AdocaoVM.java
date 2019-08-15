package br.com.ifma.adota.pet.pages;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import br.com.ifma.adota.pet.model.cliente.Cliente;
import br.com.ifma.adota.pet.model.cliente.ClienteBuilder;

public class AdocaoVM {
	
	Cliente cliente;
	
	public AdocaoVM() {
		
	}

	
	@Init
	public void init() {

		if (Sessions.getCurrent().getAttribute("usuario") == null) {
			Executions.sendRedirect("index.zul");

		} else {
			cliente = ClienteBuilder.umNovoCliente().controi();
			cliente = (Cliente) Sessions.getCurrent().getAttribute("cliente");

		}

	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

}
