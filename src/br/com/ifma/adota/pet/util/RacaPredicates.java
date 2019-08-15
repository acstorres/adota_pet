package br.com.ifma.adota.pet.util;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.ifma.adota.pet.model.raca.Raca;


public class RacaPredicates {

	Predicate<Raca> notNullPredicate = Objects::nonNull;

	
	public static Predicate<Raca> racaEquals(Integer especieId) {
		return tipoMulta -> tipoMulta.getEspecie().getEspecieId().equals(especieId);
	}
	
	//ULTIMO
	public static List<Raca> filtrarRaca(List<Raca> racas, Predicate<Raca> predicate) {
		return racas.stream().filter(predicate).collect(Collectors.toList());
	}

}
