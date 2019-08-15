package br.com.ifma.adota.pet.model.raca;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ifma.adota.pet.model.especie.Especie;

@Entity(name = Raca.NAME)
@Table(schema="adota_pet", name = "raca")
public class Raca implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "adota_pet_raca";
	
	@Id
	@Column(name = "raca_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer racaId;
	
	@Column(name = "descricao",  nullable = true)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "especie_id")
	private Especie especie;

}
