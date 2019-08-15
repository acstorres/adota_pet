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
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;

import br.com.ifma.adota.pet.model.cliente.Cliente;
import br.com.ifma.adota.pet.model.cliente.ClienteSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.endereco.Endereco;
import br.com.ifma.adota.pet.model.usuario.Usuario;
import br.com.ifma.adota.pet.model.usuario.UsuarioSessionBeanFacadeLocal;

public class IndexVM {
	private Usuario usuario;
	private Cliente cliente;
	private Endereco endereco;
	private String login, senha, conteudo = "default.zul";

	private UsuarioSessionBeanFacadeLocal usuarioSessionBeanFacadeLocal;
	private ClienteSessionBeanFacadeLocal clienteSessionBeanFacadeLocal;

	public IndexVM() {
		try {
			InitialContext ic = new InitialContext();

			usuarioSessionBeanFacadeLocal = (UsuarioSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + UsuarioSessionBeanFacadeLocal.JNDI);

			clienteSessionBeanFacadeLocal = (ClienteSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + ClienteSessionBeanFacadeLocal.JNDI);

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

		try {
			if (login == null || senha == null) {
				Clients.showNotification("Os campos login e senha devem ser preenchidos!",
						Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
				return;
			} else {

				try {

					usuario = usuarioSessionBeanFacadeLocal.findByLogin(login);
					System.out.println(usuario.getLogin());

				} catch (Exception e) {
					Clients.showNotification("Usuario e senha inválido!", Clients.NOTIFICATION_TYPE_WARNING, null, null,
							3000, true);
					return;
				}

				if (usuario == null || usuario.isAtivo() == false) {
					Clients.showNotification("Usuário não cadastrado!", Clients.NOTIFICATION_TYPE_WARNING, null, null,
							3000, true);
					return;
				} else {

					if (usuario.getSenha().trim().equals(senha.trim())) {

						cliente = clienteSessionBeanFacadeLocal.findByUsuarioId(usuario.getUsuarioId());

						if (cliente != null && cliente.isAtivo()) {


								if (cliente.isDoador()) {

									Sessions.getCurrent().setAttribute("usuario", usuario);
									Sessions.getCurrent().setAttribute("cliente", cliente);
									Executions.sendRedirect("doador.zul");
								} else {

									Sessions.getCurrent().setAttribute("cliente", cliente);
									Sessions.getCurrent().setAttribute("usuario", usuario);
									Executions.sendRedirect("adotante.zul");
								}

						}else {

							Clients.showNotification("Usuário inválido!", Clients.NOTIFICATION_TYPE_WARNING, null,
									null, 3000, true);
							return;
						}

					} else {

						Clients.showNotification("Usuário e senha não correspondem a um usuário válido!",
								Clients.NOTIFICATION_TYPE_WARNING, null, null, 3000, true);
						return;
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
