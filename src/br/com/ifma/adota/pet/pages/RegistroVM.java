package br.com.ifma.adota.pet.pages;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Vbox;

import br.com.ifma.adota.pet.model.cliente.Cliente;
import br.com.ifma.adota.pet.model.usuario.Usuario;

public class RegistroVM {


	private Usuario usuario;
	private Cliente cliente;

	private boolean doador, pf, pj;
	
	@Wire
	private Div divPf = new Div(), divPj = new Div();

	public RegistroVM() {

	}

	@Init
	public void init() {
		cliente = new Cliente();
		usuario = new Usuario();

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	
	@Command
	@NotifyChange({"divPj", "divPf", "pj"})
	public void pf() {
		pj = false;
		
		divPj.setVisible(false);
		divPf.setVisible(true);
		BindUtils.postNotifyChange(null, null, this, "pj");
		BindUtils.postNotifyChange(null, null, this, "divPf");
		BindUtils.postNotifyChange(null, null, this, "divPj");
		
	}
	
	@Command
	@NotifyChange({"divPf", "divPj", "pf"})
	public void pj() {
		
		pf = false;
		
		divPf.setVisible(false);
		divPj.setVisible(true);
		BindUtils.postNotifyChange(null, null, this, "pf");
		BindUtils.postNotifyChange(null, null, this, "divPf");
		BindUtils.postNotifyChange(null, null, this, "divPj");
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isDoador() {
		return doador;
	}

	public void setDoador(boolean doador) {
		this.doador = doador;
	}

	public boolean isPf() {
		return pf;
	}

	public void setPf(boolean pf) {
		this.pf = pf;
	}

	public boolean isPj() {
		return pj;
	}

	public void setPj(boolean pj) {
		this.pj = pj;
	}


}
