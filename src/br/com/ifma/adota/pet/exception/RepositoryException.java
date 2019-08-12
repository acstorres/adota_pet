package br.com.ifma.adota.pet.exception;

public class RepositoryException extends Exception{
	
	private static final long serialVersionUID = -8239148831324403152L;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message, Throwable causa) {
		super(message, causa);
	}

	public RepositoryException(Throwable causa) {
		super(causa);
	}
}
