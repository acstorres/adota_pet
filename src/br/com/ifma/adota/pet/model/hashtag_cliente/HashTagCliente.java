package br.com.ifma.adota.pet.model.hashtag_cliente;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ifma.adota.pet.model.cliente.Cliente;

@Entity(name = HashTagCliente.NAME)
@Table(schema = "adota_pet", name = "hashtag_cliente")
public class HashTagCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_hashtag_cliente";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hashtag_cliente_id")
	private Integer hashtagClienteId;

	@Column(name = "hashtag")
	private String hashtag;

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	@Column(columnDefinition = "tinyint(1) default 0")
	Boolean notificado;

	public HashTagCliente() {
		super();
	}

	public Integer getHashtagClienteId() {
		return hashtagClienteId;
	}

	public void setHashtagClienteId(Integer hashtagClienteId) {
		this.hashtagClienteId = hashtagClienteId;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Boolean getNotificado() {
		return notificado;
	}

	public void setNotificado(Boolean notificado) {
		this.notificado = notificado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashtagClienteId == null) ? 0 : hashtagClienteId.hashCode());
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
		HashTagCliente other = (HashTagCliente) obj;
		if (hashtagClienteId == null) {
			if (other.hashtagClienteId != null)
				return false;
		} else if (!hashtagClienteId.equals(other.hashtagClienteId))
			return false;
		return true;
	}

}
