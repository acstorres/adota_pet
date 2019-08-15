package br.com.ifma.adota.pet.model.adocao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.lancamento.Lancamento;

@Entity(name = Adocao.NAME)
@Table(schema = "adota_pet", name = "adocao")
@Transactional
public class Adocao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_adocao";

	@Id
	@Column(name = "adocao_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adocaoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lancamento_id")
	private Lancamento lancamento;
	
	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	public Adocao() {
		super();
	}

	public Adocao(Integer adocaoId) {
		super();
		this.adocaoId = adocaoId;
	}

	public Adocao(Lancamento lancamento, Animal animal) {
		super();
		this.lancamento = lancamento;
		this.animal = animal;
	}

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
	
	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	////////////////////////////////////////// Métodos de Ação

	public Boolean isLancamentoValido() {
		return (this.getLancamento() != null);
	}

	public Boolean isAnimalValido() {
		return (this.animal != null);
	}

	////////////////////////////////////////// Demais Métodos
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adocaoId == null) ? 0 : adocaoId.hashCode());
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
		Adocao other = (Adocao) obj;
		if (adocaoId == null) {
			if (other.adocaoId != null)
				return false;
		} else if (!adocaoId.equals(other.adocaoId))
			return false;
		return true;
	}

}
