package br.com.ifma.adota.pet.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;

import antlr.StringUtils;

public class StringUtil {
	
	
	/*
	 * public static boolean verificaCaracterse(String string) {
	 * if(string.contains(".,-abcdefghijklmnopqrs")) {
	 * 
	 * } }
	 */

	public static String capitalizeEveryWord(String source, List<Delimiter> delimiters, Locale locale) {
		char[] chars; 

		if (delimiters == null || delimiters.size() == 0)
			delimiters = getDefaultDelimiters();				

		// If Locale specified, i18n toLowerCase is executed, to handle specific behaviors (eg. Turkish dotted and dotless 'i')
		if (locale!=null)
			chars = source.toLowerCase(locale).toCharArray();
		else 
			chars = source.toLowerCase().toCharArray();

		// First charachter ALWAYS capitalized, if it is a Letter.
		if (chars.length>0 && Character.isLetter(chars[0]) && !isSurrogate(chars[0])){
			chars[0] = Character.toUpperCase(chars[0]);
		}

		for (int i = 0; i < chars.length; i++) {
			if (!isSurrogate(chars[i]) && !Character.isLetter(chars[i])) {
				// Current char is not a Letter; gonna check if it is a delimitrer.
				for (Delimiter delimiter : delimiters){
					if (delimiter.getDelimiter()==chars[i]){
						// Delimiter found, applying rules...						
						if (delimiter.capitalizeBefore() && i>0 
								&& Character.isLetter(chars[i-1]) && !isSurrogate(chars[i-1]))
						{   // previous character is a Letter and I have to capitalize it
							chars[i-1] = Character.toUpperCase(chars[i-1]);
						}
						if (delimiter.capitalizeAfter() && i<chars.length-1 
								&& Character.isLetter(chars[i+1]) && !isSurrogate(chars[i+1]))
						{   // next character is a Letter and I have to capitalize it
							chars[i+1] = Character.toUpperCase(chars[i+1]);
						}
						break;
					}
				} 
			}
		}
		return String.valueOf(chars);
	}

	public static String capitalizeEveryWord(String source, Locale locale) {
		return capitalizeEveryWord(source,null,locale);
	}

	public static String capitalizeEveryWord(String source) {
		return capitalizeEveryWord(source,null,null);
	}	
	
	public static String unaccent(String src) {
		return Normalizer.normalize(src, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static String unaccentAndRemoveWhiteSpace(String src) {
		String passa = src;
		passa = passa.replaceAll("-", "_");
		passa = passa.replaceAll("[+=*&%$#@!]", "");
		return Normalizer.normalize(passa.replaceAll(" ", "_"), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static String unaccentAndRemoveBlankSpace(String src) {
		String passa = src;
		passa = passa.replaceAll("-", "");
		passa = passa.replaceAll("[.,;\\//+=*&%$#@!]", "");
		return Normalizer.normalize(passa.replaceAll(" ", ""), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static String retiraCaracteresEspeciais(String stringFonte) {
		String passa = stringFonte;
		passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
		passa = passa.replaceAll("[âãàáä]", "a");
		passa = passa.replaceAll("[ÊÈÉË]", "E");
		passa = passa.replaceAll("[êèéë]", "e");
		passa = passa.replaceAll("[ÎÍÌÏ]", "I");
		passa = passa.replaceAll("[îíìï]", "i");
		passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
		passa = passa.replaceAll("[ôõòóö]", "o");
		passa = passa.replaceAll("[ÛÙÚÜ]", "U");
		passa = passa.replaceAll("[ûúùü]", "u");
		passa = passa.replaceAll("Ç", "C");
		passa = passa.replaceAll("ç", "c");
		passa = passa.replaceAll("[ýÿ]", "y");
		passa = passa.replaceAll("Ý", "Y");
		passa = passa.replaceAll("ñ", "n");
		passa = passa.replaceAll("Ñ", "N");
		passa = passa.replaceAll("[-+=*&%$#@!_]", "");
		passa = passa.replaceAll("['\"]", "");
		passa = passa.replaceAll("[<>()\\{\\}]", "");
		passa = passa.replaceAll("['\\\\,()|/]", "");
		passa = passa.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
		return passa;
	}

	private static boolean isSurrogate(char chr){
		// Check if the current character is part of an UTF-16 Surrogate Pair.	
		// Note: not validating the pair, just used to bypass (any found part of) it.
		return (Character.isHighSurrogate(chr) || Character.isLowSurrogate(chr));
	}		

	private static List<Delimiter> getDefaultDelimiters(){
		// If no delimiter specified, "Capitalize after space" rule is set by default. 
		List<Delimiter> delimiters = new ArrayList<Delimiter>();
		delimiters.add(new Delimiter(Behavior.CAPITALIZE_AFTER_MARKER, ' '));
		return delimiters;
	} 

	static class Delimiter {
		private Behavior behavior;
		private char delimiter;

		private Delimiter(Behavior behavior, char delimiter) {
			super();
			this.behavior = behavior;
			this.delimiter = delimiter;
		}

		public boolean capitalizeBefore(){
			return (behavior.equals(Behavior.CAPITALIZE_BEFORE_MARKER)
					|| behavior.equals(Behavior.CAPITALIZE_BEFORE_AND_AFTER_MARKER));
		}

		public boolean capitalizeAfter(){
			return (behavior.equals(Behavior.CAPITALIZE_AFTER_MARKER)
					|| behavior.equals(Behavior.CAPITALIZE_BEFORE_AND_AFTER_MARKER));
		}

		public char getDelimiter() {
			return delimiter;
		}
	}

	static enum Behavior {
		CAPITALIZE_AFTER_MARKER(0),
		CAPITALIZE_BEFORE_MARKER(1),
		CAPITALIZE_BEFORE_AND_AFTER_MARKER(2);						

		private int value;			

		private Behavior(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}			
	}
	
	 public static boolean isCpfValido(String CPF) {
	        CPF = inserirCaracteres('0', CPF, 11, true);
	        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
	                CPF.equals("22222222222") || CPF.equals("33333333333") ||
	                CPF.equals("44444444444") || CPF.equals("55555555555") ||
	                CPF.equals("66666666666") || CPF.equals("77777777777") ||
	                CPF.equals("88888888888") || CPF.equals("99999999999") ||
	                (CPF.length() != 11))
	            return (false);

	        char dig10, dig11;
	        int sm, i, r, num, peso;

	        try {
	            sm = 0;
	            peso = 10;
	            for (i = 0; i < 9; i++) {
	                num = (int) (CPF.charAt(i) - 48);
	                sm = sm + (num * peso);
	                peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig10 = '0';
	            else dig10 = (char) (r + 48);

	            sm = 0;
	            peso = 11;
	            for (i = 0; i < 10; i++) {
	                num = (int) (CPF.charAt(i) - 48);
	                sm = sm + (num * peso);
	                peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig11 = '0';
	            else dig11 = (char) (r + 48);

	            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
	                return (true);
	            else return (false);
	        } catch (InputMismatchException erro) {
	            return (false);
	        }
	    }
	 
	 
	 public static boolean isCnpjValido(String CNPJ) {
	        CNPJ = inserirCaracteres('0', CNPJ, 14, true);
	        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
	                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
	                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
	                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
	                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
	                (CNPJ.length() != 14))
	            return (false);

	        char dig13, dig14;
	        int sm, i, r, num, peso;

	        try {
	            sm = 0;
	            peso = 2;
	            for (i = 11; i >= 0; i--) {
	                num = (int) (CNPJ.charAt(i) - 48);
	                sm = sm + (num * peso);
	                peso = peso + 1;
	                if (peso == 10)
	                    peso = 2;
	            }

	            r = sm % 11;
	            if ((r == 0) || (r == 1))
	                dig13 = '0';
	            else dig13 = (char) ((11 - r) + 48);

	            sm = 0;
	            peso = 2;
	            for (i = 12; i >= 0; i--) {
	                num = (int) (CNPJ.charAt(i) - 48);
	                sm = sm + (num * peso);
	                peso = peso + 1;
	                if (peso == 10)
	                    peso = 2;
	            }

	            r = sm % 11;
	            if ((r == 0) || (r == 1))
	                dig14 = '0';
	            else dig14 = (char) ((11 - r) + 48);

	            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
	                return (true);
	            else return (false);
	        } catch (InputMismatchException erro) {
	            return (false);
	        }
	    }
	 
	 /**
	   * Método utilizado para inserir caracteres à esquerda ou direita de uma
	   * String
	   * 
	   * @param caractere
	   *            é o caractere utilizado para completar a palavra
	   * @param palavra
	   *            é a palavra na qual que queremos inserir caracteres
	   * @param tamanhoFinalPalavra
	   *            tamanho da palavra após a inserção de caracteres
	   * @param esquerda
	   *            TRUE se os caracteres serão inseridos a esquerda, FALSE caso
	   *            eles sejam inseridos a direita
	   * @return uma string com o tamanho especificado completado com caracteres
	   *         de acordo com o sentido especificado
	   */
	 public static String inserirCaracteres(char caractere, String palavra,
		      int tamanhoFinalPalavra, boolean esquerda) {
		    int quantidadeCaracteresInserir = tamanhoFinalPalavra
		        - palavra.length();
		    String novaPalavra = "";

		    for (int i = 1; i <= quantidadeCaracteresInserir; i++)
		      novaPalavra += caractere;

		    if (esquerda) {
		      novaPalavra = novaPalavra + palavra;
		    } else {
		      novaPalavra = palavra + novaPalavra;
		    }

		    return novaPalavra;
		  }
}