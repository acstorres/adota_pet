package br.com.ifma.adota.pet.model.adocao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.ifma.adota.pet.model.especie.Especie;

@Entity(name = Raca.NAME)
@Table(schema = "adota_pet", name = "raca")
public class Raca implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_raca";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "raca_id")
	private Long racaId;

	@Column(name = "descricao")
	private String descricao;

	@OneToOne
	@JoinColumn(name = "especie_id")
	private Especie especie;

	public Raca() {
		super();
	}

	public Long getRacaId() {
		return racaId;
	}

	public void setRaca_id(Long racaId) {
		this.racaId = racaId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

}
