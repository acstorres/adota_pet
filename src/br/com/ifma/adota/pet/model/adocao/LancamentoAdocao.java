package br.com.ifma.adota.pet.model.adocao;

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

import br.com.ifma.adota.pet.model.cliente.Cliente;

@Entity(name = LancamentoAdocao.NAME)
@Table(schema = "adota_pet", name = "lancamento_acao")
@Transactional
public class LancamentoAdocao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_lancamento_acao";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lancamento_acao_id")
	private Long lancamentoAcaoId;

	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Fetch(FetchMode.SELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lancamentoAdocao")
	private List<MovimentoAdocao> movimentosAdocao = new ArrayList<MovimentoAdocao>();

	public LancamentoAdocao() {
		super();
	}

	public LancamentoAdocao(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Long getLancamentoAcaoId() {
		return lancamentoAcaoId;
	}

	public void setLancamentoAcaoId(Long lancamentoAcaoId) {
		this.lancamentoAcaoId = lancamentoAcaoId;
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

	public List<MovimentoAdocao> getMovimentoAdocao() {
		return this.movimentosAdocao;
	}

	public void adicionaMovimentoAdocao(MovimentoAdocao movimentoAdocao) {
		movimentoAdocao.setLancamentoAdocao(this);
		this.movimentosAdocao.add(movimentoAdocao);
	}

	public void adicionarMovimentosAdocao(List<MovimentoAdocao> movimentosAdocao) {
		movimentosAdocao.stream().forEach(i -> {
			i.setLancamentoAdocao(this);
			this.movimentosAdocao.add(i);
		});
	}

	////////////////////////////////////////// Métodos de Ação

	public void criarMovimentosAdocao(Animal animal) {
		if (this.movimentosAdocao == null)
			this.movimentosAdocao = new ArrayList<MovimentoAdocao>();

		MovimentoAdocao movimentoAdocao = MovimentoAdocaoBuilder.umNovoMovimentoAdocao(this, animal)
				.definidoAutomaticamentePelaDataLancamento().constroi();

		this.adicionaMovimentoAdocao(movimentoAdocao);
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
		if (this.getMovimentoAdocao() == null
				&& (this.getMovimentoAdocao() != null && this.getMovimentoAdocao().isEmpty()))
			return Boolean.FALSE;

		int qtdeElementos = this.getMovimentoAdocao().size();
		int qtdeElementosValidos = 0;

		for (MovimentoAdocao movimentoAdocao : this.getMovimentoAdocao()) {
			if (movimentoAdocao.isAnimalValido() && movimentoAdocao.isLancamentoValido())
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
		result = prime * result + ((lancamentoAcaoId == null) ? 0 : lancamentoAcaoId.hashCode());
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
		LancamentoAdocao other = (LancamentoAdocao) obj;
		if (lancamentoAcaoId == null) {
			if (other.lancamentoAcaoId != null)
				return false;
		} else if (!lancamentoAcaoId.equals(other.lancamentoAcaoId))
			return false;
		return true;
	}
}
