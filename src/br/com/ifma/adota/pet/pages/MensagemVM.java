package br.com.ifma.adota.pet.pages;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

public class MensagemVM {
	
	
	private String nome, mensagem, email;
	
	public MensagemVM() {
		
	}

	
	
	@Init
	public void init() {


	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getMensagem() {
		return mensagem;
	}



	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}

}
