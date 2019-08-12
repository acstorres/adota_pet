package br.com.ifma.adota.pet.model.usuario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = Usuario.NAME)
@Table(schema = "adota_pet", name = "usuario")
@XmlRootElement
@Transactional
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_usuario";

	@Id
	@Column(name = "usuario_id", unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	private Long usuarioId;

	@Column(name = "login", nullable = true)
	private String login;

	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

}
