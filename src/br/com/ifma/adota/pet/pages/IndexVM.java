package br.com.ifma.adota.pet.pages;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

import br.com.ifma.adota.pet.model.usuario.Usuario;
import br.com.ifma.adota.pet.model.usuario.UsuarioSessionBeanFacadeLocal;

public class IndexVM {
	private Usuario usuario;
	private String login, senha, conteudo = "default.zul";

	private UsuarioSessionBeanFacadeLocal usuarioSessionBeanFacadeLocal;

	public IndexVM() {
		try {
			InitialContext ic = new InitialContext();

			usuarioSessionBeanFacadeLocal = (UsuarioSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + UsuarioSessionBeanFacadeLocal.JNDI);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Init
	public void init() {

	}

	@Command
	@NotifyChange(".")
	public void irPara(@BindingParam("pagina") String pagina) {

		switch (pagina) {
		case "registro":
			conteudo = "registro.zul";
			break;

		default:
			conteudo = "default.zul";
			break;
		}
	}

	@Command
	public void entrar() {

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

}
