package br.com.ifma.adota.pet.infraestrutura;

import org.hibernate.HibernateException;

public class DaoRepositoryException extends HibernateException {

	private static final long serialVersionUID = 1L;
	private Exception hiddenException;

	public DaoRepositoryException(String error, Exception exception) {
		super(error);
		hiddenException = exception;
	}

	public DaoRepositoryException(String message) {
		super(message);
	}

	public Exception getHiddenException() {
		return hiddenException;
	}

	public void setHiddenException(Exception hiddenException) {
		this.hiddenException = hiddenException;
	}
}
