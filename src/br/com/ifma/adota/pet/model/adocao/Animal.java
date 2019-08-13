package br.com.ifma.adota.pet.model.adocao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifma.adota.pet.model.cliente.Cliente;

@Entity(name = Animal.NAME)
@Table(schema = "adota_pet", name = "animal")
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_animal";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "animal_id")
	private Long animalId;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "porte")
	private String porte;
	
	@Column(name = "idade_estimada")
	private Integer idadeEstimada;
	
	@Column(name = "cor_pelagem")
	private String corPelagem;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean cadastrado;
	
	@Column(name = "sinais_marcas")
	private String sinaisMarcas;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private Boolean adotado;
	
	@OneToOne
	@JoinColumn(name = "raca_id")
	private Raca raca;
	
	@OneToOne
	@JoinColumn(name = "doador_cliente_id")
	private Cliente cliente;
	
	public Animal() {
		super();		
	}

	public Long getAnimalId() {
		return animalId;
	}

	public void setAnimalId(Long animalId) {
		this.animalId = animalId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public Integer getIdadeEstimada() {
		return idadeEstimada;
	}

	public void setIdadeEstimada(Integer idadeEstimada) {
		this.idadeEstimada = idadeEstimada;
	}

	public String getCorPelagem() {
		return corPelagem;
	}

	public void setCorPelagem(String corPelagem) {
		this.corPelagem = corPelagem;
	}

	public Boolean getCadastrado() {
		return cadastrado;
	}

	public void setCadastrado(Boolean cadastrado) {
		this.cadastrado = cadastrado;
	}

	public String getSinaisMarcas() {
		return sinaisMarcas;
	}

	public void setSinaisMarcas(String sinaisMarcas) {
		this.sinaisMarcas = sinaisMarcas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getAdotado() {
		return adotado;
	}

	public void setAdotado(Boolean adotado) {
		this.adotado = adotado;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
