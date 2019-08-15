package br.com.ifma.adota.pet.model.especie;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = Especie.NAME)
@Table(schema="adota_pet", name = "especie")
public class Especie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_especie";
	
	@Id
	@Column(name = "especie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer especieId;
	
	@Column(name = "descricao",  nullable = true)
	private String descricao;

	public Integer getEspecieId() {
		return especieId;
	}

	public void setEspecieId(Integer especieId) {
		this.especieId = especieId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((especieId == null) ? 0 : especieId.hashCode());
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
		Especie other = (Especie) obj;
		if (especieId == null) {
			if (other.especieId != null)
				return false;
		} else if (!especieId.equals(other.especieId))
			return false;
		return true;
	}
	
	
	
	
}
