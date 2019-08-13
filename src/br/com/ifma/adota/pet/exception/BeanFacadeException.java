package br.com.ifma.adota.pet.exception;

public class BeanFacadeException extends Exception{

	private static final long serialVersionUID = -8239148831324403152L;
	
	public BeanFacadeException(String message) {
		super(message);
	}

	public BeanFacadeException(String message, Throwable causa) {
		super(message, causa);
	}

	public BeanFacadeException(Throwable causa) {
		super(causa);
	}
}
