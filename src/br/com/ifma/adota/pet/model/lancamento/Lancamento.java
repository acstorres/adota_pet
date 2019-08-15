package br.com.ifma.adota.pet.model.lancamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.ifma.adota.pet.model.adocao.Adocao;
import br.com.ifma.adota.pet.model.adocao.AdocaoBuilder;
import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.cliente.Cliente;

@Entity(name = Lancamento.NAME)
@Table(schema = "adota_pet", name = "lancamento")
@Transactional
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_lancamento";

	@Id
	@Column(name = "lancamento_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lancamentoId;

	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Fetch(FetchMode.SELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lancamento")
	private List<Adocao> adocao = new ArrayList<Adocao>();

	public Lancamento() {
		super();
	}

	public Lancamento(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Integer getLancamentoId() {
		return lancamentoId;
	}

	public void setLancamentoId(Integer lancamentoId) {
		this.lancamentoId = lancamentoId;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Adocao> getAdocao() {
		return this.adocao;
	}

	public void adicionaAdocao(Adocao adocao) {
		adocao.setLancamento(this);
		this.adocao.add(adocao);
	}

	public void adicionarMovimentosAdocao(List<Adocao> Adocao) {
		adocao.stream().forEach(i -> {
			i.setLancamento(this);
			this.adocao.add(i);
		});
	}

	////////////////////////////////////////// Métodos de Ação

	public void criarMovimentosAdocao(Animal animal) {
		if (this.adocao == null)
			this.adocao = new ArrayList<Adocao>();

		Adocao Adocao = AdocaoBuilder.umNovoMovimentoAdocao(this, animal).definidoAutomaticamentePelaDataLancamento()
				.constroi();

		this.adicionaAdocao(Adocao);
	}

	public void criarMovimentosAdocao(List<Animal> animais) {
		animais.stream().forEach(animal -> {
			this.criarMovimentosAdocao(animal);
		});
	}

	public Boolean isClienteValido() {
		return (this.getCliente() != null);
	}

	public Boolean isMovimentosAdocaoValido() {
		if (this.adocao == null && (this.adocao != null && this.adocao.isEmpty()))
			return Boolean.FALSE;

		int qtdeElementos = this.adocao.size();
		int qtdeElementosValidos = 0;

		for (Adocao a : this.adocao) {
			if (a.isAnimalValido() && a.isLancamentoValido())
				qtdeElementosValidos++;
		}

		if (qtdeElementos != qtdeElementosValidos)
			return Boolean.FALSE;

		return Boolean.TRUE;
	}

	////////////////////////////////////////// Demais Métodos

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lancamentoId == null) ? 0 : lancamentoId.hashCode());
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
		Lancamento other = (Lancamento) obj;
		if (lancamentoId == null) {
			if (other.lancamentoId != null)
				return false;
		} else if (!lancamentoId.equals(other.lancamentoId))
			return false;
		return true;
	}
}
