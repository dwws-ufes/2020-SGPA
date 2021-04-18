package br.ufes.informatica.doeLivros.core.exceptions;

public class EmailInUseException extends Exception {
	/** The unique identifier for a serializable class. */
	private static final long serialVersionUID = 1L;

	/** The e-mail that is already in use. */
	private String email;

	/** Constructor. */
	public EmailInUseException(String email) {
		this.email = email;
	}

	/** Getter for email. */
	public String getEmail() {
		return email;
	}
}