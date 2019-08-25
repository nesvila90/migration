package co.gov.sic.migration.commons.exceptions;

/**
 * 
 * @author sarias
 *
 */

public class WebSecurityException extends Exception {

	private static final long serialVersionUID = 1L;

	public WebSecurityException() {
		super();
	}

	public WebSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebSecurityException(String message) {
		super(message);
	}

	public WebSecurityException(Throwable cause) {
		super(cause);
	}

}
