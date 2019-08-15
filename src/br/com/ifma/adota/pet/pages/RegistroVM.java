package br.com.ifma.adota.pet.pages;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.BindUtils;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

import br.com.ifma.adota.pet.model.cliente.Cliente;
import br.com.ifma.adota.pet.model.cliente.ClienteBuilder;
import br.com.ifma.adota.pet.model.cliente.ClienteSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.endereco.Endereco;
import br.com.ifma.adota.pet.model.endereco.EnderecoBuilder;
import br.com.ifma.adota.pet.model.endereco.EnderecoSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.model.usuario.Usuario;
import br.com.ifma.adota.pet.model.usuario.UsuarioBuilder;
import br.com.ifma.adota.pet.model.usuario.UsuarioSessionBeanFacadeLocal;
import br.com.ifma.adota.pet.util.StringUtil;

public class RegistroVM {

	private Usuario usuario;
	private Cliente cliente;
	private Endereco endereco;

	private UsuarioSessionBeanFacadeLocal usuarioSessionBeanFacadeLocal;
	private ClienteSessionBeanFacadeLocal clienteSessionBeanFacadeLocal;
	private EnderecoSessionBeanFacadeLocal enderecoSessionBeanFacadeLocal;

	private boolean pf, pj, doador;

	@Wire
	private Div divPf = new Div(), divPj = new Div();

	@Wire
	private Div divLogin = new Div();

	@Wire
	private Div divEndereco = new Div();

	@Wire
	private Div divContato = new Div();

	@Wire
	private Div divDoador = new Div();

	@Wire
	private Div divFormulario = new Div();

	public RegistroVM() {
		try {
			InitialContext ic = new InitialContext();

			usuarioSessionBeanFacadeLocal = (UsuarioSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + UsuarioSessionBeanFacadeLocal.JNDI);

			clienteSessionBeanFacadeLocal = (ClienteSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + ClienteSessionBeanFacadeLocal.JNDI);

			enderecoSessionBeanFacadeLocal = (EnderecoSessionBeanFacadeLocal) ic
					.lookup("java:global/adota_pet/" + EnderecoSessionBeanFacadeLocal.JNDI);

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	@Init
	public void init() {
		cliente = new Cliente();
		usuario = new Usuario();
		endereco = new Endereco();

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "divPf", "divPj", "pf", "divEndereco", "divContato", "divLogin", "cliente", "endereco", "usuario" })
	public void pf() {

		chamaBuilders();

		pj = false;

		divPj.setVisible(false);
		divEndereco.setVisible(false);
		divContato.setVisible(false);
		divLogin.setVisible(false);

		divPf.setVisible(true);
		BindUtils.postNotifyChange(null, null, this, "pj");
		BindUtils.postNotifyChange(null, null, this, "divPf");
		BindUtils.postNotifyChange(null, null, this, "divPj");

		BindUtils.postNotifyChange(null, null, this, "divEndereco");
		BindUtils.postNotifyChange(null, null, this, "divContato");
		BindUtils.postNotifyChange(null, null, this, "divLogin");

		BindUtils.postNotifyChange(null, null, this, "cliente");
		BindUtils.postNotifyChange(null, null, this, "endereco");
		BindUtils.postNotifyChange(null, null, this, "usuario");
	}

	public void chamaBuilders() {
		cliente = ClienteBuilder.umNovoCliente().definidoAutomaticamentePelaDataLancamento().controi();
		endereco = EnderecoBuilder.umNovoEndereco().controi();
		usuario = UsuarioBuilder.umNovoUsuario().definidoAutomaticamentePelaDataLancamento().controi();
	}

	@Command
	@NotifyChange({ "divPf", "divPj", "pf", "divEndereco", "divContato", "divLogin", "cliente", "endereco", "usuario" })
	public void pj() {

		chamaBuilders();

		pf = false;

		divPf.setVisible(false);
		divEndereco.setVisible(false);
		divContato.setVisible(false);
		divLogin.setVisible(false);

		divPj.setVisible(true);
		BindUtils.postNotifyChange(null, null, this, "pf");
		BindUtils.postNotifyChange(null, null, this, "divPf");
		BindUtils.postNotifyChange(null, null, this, "divPj");

		BindUtils.postNotifyChange(null, null, this, "divEndereco");
		BindUtils.postNotifyChange(null, null, this, "divContato");
		BindUtils.postNotifyChange(null, null, this, "divLogin");

		BindUtils.postNotifyChange(null, null, this, "cliente");
		BindUtils.postNotifyChange(null, null, this, "endereco");
		BindUtils.postNotifyChange(null, null, this, "usuario");

	}

	// TODO validar anterior
	@Command
	@NotifyChange({ "divEndereco", "divPf", "divPj" })
	public void proximoPessoal() {

		divPf.setVisible(false);
		divPj.setVisible(false);

		//TODO validar aqui
		
		if(cliente.getDataNascimento() == null ) {
		}
		if(cliente.getCpf() == null) {
			
		}
		
		if(cliente.getNome() == null) {
			
		}
		
		
		
		divEndereco.setVisible(true);

		BindUtils.postNotifyChange(null, null, this, "divEndereco");
		BindUtils.postNotifyChange(null, null, this, "divPf");
		BindUtils.postNotifyChange(null, null, this, "divPj");

	}

	@Command
	@NotifyChange({ "divEndereco", "divPf", "divPj", "cliente" })
	public void anteriorEndereco() {

		divEndereco.setVisible(false);
		System.out.println(pf);
		if (pf) {
			divPf.setVisible(true);
		} else {
			divPj.setVisible(true);
		}

		BindUtils.postNotifyChange(null, null, this, "divEndereco");
		BindUtils.postNotifyChange(null, null, this, "divPf");
		BindUtils.postNotifyChange(null, null, this, "divPj");
		BindUtils.postNotifyChange(null, null, this, "cliente");

	}

	@Command
	@NotifyChange({ "divEndereco", "divContato", "cliente" })
	public void proximoEndereco() {

		divEndereco.setVisible(false);
		divContato.setVisible(true);

		BindUtils.postNotifyChange(null, null, this, "divEndereco");
		BindUtils.postNotifyChange(null, null, this, "divContato");
		BindUtils.postNotifyChange(null, null, this, "cliente");

	}

	@Command
	@NotifyChange({ "divEndereco", "divContato", "endereco" })
	public void anteriorContato() {

		divContato.setVisible(false);
		divEndereco.setVisible(true);

		BindUtils.postNotifyChange(null, null, this, "divEndereco");
		BindUtils.postNotifyChange(null, null, this, "divContato");
		BindUtils.postNotifyChange(null, null, this, "endereco");

	}

	@Command
	@NotifyChange({ "divLogin", "divContato", "usuario" })
	public void proximoContato() {

		divContato.setVisible(false);

		divLogin.setVisible(true);

		BindUtils.postNotifyChange(null, null, this, "divLogin");
		BindUtils.postNotifyChange(null, null, this, "divContato");
		BindUtils.postNotifyChange(null, null, this, "usuario");

	}

	@Command
	@NotifyChange({ "divLogin", "divContato", "cliente" })
	public void anteriorLogin() {

		divLogin.setVisible(false);

		divContato.setVisible(true);

		BindUtils.postNotifyChange(null, null, this, "divLgin");
		BindUtils.postNotifyChange(null, null, this, "divContato");
		BindUtils.postNotifyChange(null, null, this, "cliente");

	}

	// TODO salvar cliente, endereco e usuario
	@Command
	@NotifyChange({ "divFormulario", "divDoador" })
	public void proximoLogin() {

		divFormulario.setVisible(false);
		divDoador.setVisible(true);

		BindUtils.postNotifyChange(null, null, this, "divFormulario");
		BindUtils.postNotifyChange(null, null, this, "divDoador");

	}

	@Command
	public void criarConta(@BindingParam("doador") boolean doador) {

		try {
			this.doador = doador;
			usuario.setAtivo(true);

			if (usuarioSessionBeanFacadeLocal.include(usuario) != null
					&& enderecoSessionBeanFacadeLocal.include(endereco) != null) {
				cliente.setEndereco(endereco);
				cliente.setUsuario(usuario);

				cliente.setAtivo(true);
				cliente.setDoador(this.doador);

				if (clienteSessionBeanFacadeLocal.include(cliente) != null) {

					Clients.showNotification("Conta criada com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null,
							3000, true);

					if (cliente.isDoador()) {
						Sessions.getCurrent().setAttribute("usuario", usuario);
						Sessions.getCurrent().setAttribute("cliente", cliente);
						Executions.sendRedirect("doador.zul");

					} else {
						Sessions.getCurrent().setAttribute("usuario", usuario);
						Sessions.getCurrent().setAttribute("cliente", cliente);
						Executions.sendRedirect("adotante.zul");

					}

				} else {
					Clients.showNotification("Erro ao criar conta!", Clients.NOTIFICATION_TYPE_WARNING, null, null,
							3000, true);
					return;

				}
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isDoador() {
		return doador;
	}

	public void setDoador(boolean doador) {
		this.doador = doador;
	}

}
