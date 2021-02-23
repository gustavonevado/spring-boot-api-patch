package br.com.api.patch.exceptions;

public class NotFound extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NotFound(String s, final String message) {

		super(message);
	}

}
