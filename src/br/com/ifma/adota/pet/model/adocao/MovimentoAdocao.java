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

@Entity(name = MovimentoAdocao.NAME)
@Table(schema = "adota_pet", name = "movimento_adocao")
@Transactional
public class MovimentoAdocao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_movimento_adocao";

	@Id
	@Column(name = "movimento_adocao_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movimentoAdocaoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lancamento_acao_id")
	private LancamentoAdocao lancamentoAdocao;

	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	public MovimentoAdocao() {
		super();
	}

	public MovimentoAdocao(Integer movimentoAdocaoId) {
		super();
		this.movimentoAdocaoId = movimentoAdocaoId;
	}

	public MovimentoAdocao(LancamentoAdocao lancamentoAdocao, Animal animal) {
		super();
		this.lancamentoAdocao = lancamentoAdocao;
		this.animal = animal;
	}

	public Integer getMovimentoAdocaoId() {
		return movimentoAdocaoId;
	}

	public void setMovimentoAdocaoId(Integer movimentoAdocaoId) {
		this.movimentoAdocaoId = movimentoAdocaoId;
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

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	////////////////////////////////////////// Métodos de Ação

	public Boolean isLancamentoValido() {
		return (this.getLancamentoAdocao() != null);
	}

	public Boolean isAnimalValido() {
		return (this.animal != null);
	}

	////////////////////////////////////////// Demais Métodos

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movimentoAdocaoId == null) ? 0 : movimentoAdocaoId.hashCode());
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
		if (movimentoAdocaoId == null) {
			if (other.movimentoAdocaoId != null)
				return false;
		} else if (!movimentoAdocaoId.equals(other.movimentoAdocaoId))
			return false;
		return true;
	}
}
