package br.com.ifma.adota.pet.pages;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import br.com.ifma.adota.pet.model.cliente.Cliente;
import br.com.ifma.adota.pet.model.cliente.ClienteBuilder;

public class AdotanteVM {

	private Cliente cliente;

	private String conteudo = "default.zul";

	public AdotanteVM() {

	}

	@Init
	public void init() {

		if (Sessions.getCurrent().getAttribute("usuario") == null) {
			Executions.sendRedirect("index.zul");

		} else {
			cliente = ClienteBuilder.umNovoCliente().constroi();
			cliente = (Cliente) Sessions.getCurrent().getAttribute("cliente");

		}

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange(".")
	public void irPara(@BindingParam("pagina") String pagina) {

		switch (pagina) {
		case "adocao":
			conteudo = "adocao.zul";
			break;
		case "inicia":
			conteudo = "default.zul";
			break;
		case "muralPets":
			conteudo = "muralPets.zul";
			break;

		default:
			conteudo = "default.zul";
			break;
		}
	}

	@Command
	public void sair() {
		Sessions.getCurrent().invalidate();
		Executions.sendRedirect("index.zul");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
