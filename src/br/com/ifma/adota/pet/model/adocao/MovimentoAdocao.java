package br.com.ifma.adota.pet.model.adocao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = MovimentoAdocao.NAME)
@Table(schema = "adota_pet", name = "movimento_adocao")
public class MovimentoAdocao implements Serializable, Comparable<MovimentoAdocao>, Cloneable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_movimento_adocao";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "movimento_adocao_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "lancamento_acao_id")
	private LancamentoAdocao lancamentoAdocao;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public LancamentoAdocao getLancamentoAdocao() {
		return lancamentoAdocao;
	}

	public void setLancamentoAdocao(LancamentoAdocao lancamentoAdocao) {
		this.lancamentoAdocao = lancamentoAdocao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MovimentoAdocao other = (MovimentoAdocao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(MovimentoAdocao o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
