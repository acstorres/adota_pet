package br.com.ifma.adota.pet.model.lancamento;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifma.adota.pet.model.cliente.Cliente;

@Entity(name = Lancamento.NAME)
@Table(schema = "adota_pet", name = "lancamento")
public class Lancamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_lancamento";

	@Id
	@Column(name = "lancamento_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lancamentoId;

	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;


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

	public Integer getLancamentoId() {
		return lancamentoId;
	}

	public void setLancamentoId(Integer lancamentoId) {
		this.lancamentoId = lancamentoId;
	}

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
