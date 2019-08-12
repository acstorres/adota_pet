package br.com.ifma.adota.pet.model.especie;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = Especie.NAME)
@Table(schema="adota_pet", name = "especie")
public class Especie{
	
	public static final String NAME = "adota_pet_especie";
	
	@Id
	@Column(name = "especie_id")
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
	
	
}
