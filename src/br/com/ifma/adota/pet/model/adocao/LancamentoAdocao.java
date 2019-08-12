package br.com.ifma.adota.pet.model.adocao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifma.adota.pet.model.cliente.Cliente;

@Entity(name = LancamentoAdocao.NAME)
@Table(schema = "adota_pet", name = "lancamento_acao")
public class LancamentoAdocao implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_lancamento_acao";

	@Id
	@Column(name = "lancamento_acao_id")
	private Long lancamentoAcaoId;

	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

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
