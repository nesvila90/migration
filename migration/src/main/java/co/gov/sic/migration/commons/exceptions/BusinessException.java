package co.gov.sic.migration.commons.exceptions;


import co.gov.sic.migration.commons.enums.CodigoError;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private CodigoError codigoError;

	public BusinessException() {
		super();
	}

	public BusinessException(final String message, final Throwable cause, final CodigoError codigoError) {
		super(message, cause);
		this.codigoError = codigoError;
	}

	public CodigoError getCodigoError() {
		return codigoError;
	}
}
