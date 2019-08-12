package br.com.ifma.adota.pet.model.cliente;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifma.adota.pet.model.usuario.Usuario;


@Entity(name = Cliente.NAME)
@Table(schema="adota_pet", name = "cliente")
public class Cliente{
	
	public static final String NAME = "adota_pet_cliente";
	
	@Id
	@Column(name = "cliente_id")
	private Integer clienteId;
	
	@Column(name = "nome",  nullable = true)
	private String nome;
	
	@Column(name = "cpf")
	private String cpf; 
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "razao_social")
	private String razaoSocial;
	
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;
	
	@Column(name = "doador")
	private Boolean doador;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	//TODO criar demais variaveis
	
	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}


	public Boolean getDoador() {
		return doador;
	}

	public void setDoador(Boolean doador) {
		this.doador = doador;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	
	
	
	
	
	
}
