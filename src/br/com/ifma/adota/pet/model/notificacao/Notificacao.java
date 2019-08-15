package br.com.ifma.adota.pet.model.notificacao;

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

import br.com.ifma.adota.pet.model.animal.Animal;
import br.com.ifma.adota.pet.model.cliente.Cliente;

@Entity(name = Notificacao.NAME)
@Table(schema = "adota_pet", name = "notificacao")
public class Notificacao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_notificacao";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notificao_id")
	private Integer notificaoId;

	@Column(name = "hashtag")
	private String hashtag;

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	@Column(columnDefinition = "tinyint(1) default 0")
	Boolean notifica;

	public Notificacao() {
		super();
	}

	public Integer getNotificaoId() {
		return notificaoId;
	}

	public void setNotificaoId(Integer notificaoId) {
		this.notificaoId = notificaoId;
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

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Boolean getNotifica() {
		return notifica;
	}

	public void setNotifica(Boolean notifica) {
		this.notifica = notifica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notificaoId == null) ? 0 : notificaoId.hashCode());
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
		Notificacao other = (Notificacao) obj;
		if (notificaoId == null) {
			if (other.notificaoId != null)
				return false;
		} else if (!notificaoId.equals(other.notificaoId))
			return false;
		return true;
	}

}
