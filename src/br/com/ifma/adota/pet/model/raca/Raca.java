package br.com.ifma.adota.pet.model.raca;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ifma.adota.pet.model.especie.Especie;

@Entity(name = Raca.NAME)
@Table(schema="adota_pet", name = "raca")
public class Raca implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_raca";
	
	@Id
	@Column(name = "raca_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer racaId;
	
	@Column(name = "descricao",  nullable = true)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "especie_id")
	private Especie especie;

	public Integer getRacaId() {
		return racaId;
	}

	public void setRacaId(Integer racaId) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((racaId == null) ? 0 : racaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Raca other = (Raca) obj;
		if (racaId == null) {
			if (other.racaId != null)
				return false;
		} else if (!racaId.equals(other.racaId))
			return false;
		return true;
	}
	
	

}
