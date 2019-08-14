package br.com.ifma.adota.pet.model.hashtag_animal;

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

import br.com.ifma.adota.pet.model.adocao.Animal;

@Entity(name = HashTagAnimal.NAME)
@Table(schema = "adota_pet", name = "hashtag_animal")
public class HashTagAnimal implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_hashtag_animal";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hashtag_animal_id")
	private Integer hashtagAnimalId;

	@Column(name = "hashtag")
	private String hashtag;

	@OneToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@Column(name = "data_lancamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLancamento;

	@Column(columnDefinition = "tinyint(1) default 0")
	Boolean notificado;

	public HashTagAnimal() {
		super();
	}

	public Integer getHashtagAnimalId() {
		return hashtagAnimalId;
	}

	public void setHashtagAnimalId(Integer hashtagAnimalId) {
		this.hashtagAnimalId = hashtagAnimalId;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
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
		result = prime * result + ((hashtagAnimalId == null) ? 0 : hashtagAnimalId.hashCode());
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
		HashTagAnimal other = (HashTagAnimal) obj;
		if (hashtagAnimalId == null) {
			if (other.hashtagAnimalId != null)
				return false;
		} else if (!hashtagAnimalId.equals(other.hashtagAnimalId))
			return false;
		return true;
	}
}
