package br.com.ifma.adota.pet.model.adocao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.lancamento.Lancamento;

@Entity(name = Adocao.NAME)
@Table(schema="adota_pet", name = "adocao")
public class Adocao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_adocao";
	
	@Id
	@Column(name = "adocao_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adocaoId;
	
	@OneToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;
	
	@ManyToOne
	@JoinColumn(name = "lancamento_id")
	private Lancamento lancamento;

	public Integer getAdocaoId() {
		return adocaoId;
	}

	public void setAdocaoId(Integer adocaoId) {
		this.adocaoId = adocaoId;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	

	
}
