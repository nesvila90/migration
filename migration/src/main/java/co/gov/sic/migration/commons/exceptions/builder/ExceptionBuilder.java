package co.gov.sic.migration.commons.exceptions.builder;


import co.gov.sic.migration.commons.enums.CodigoError;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;

/**
 * Created by jrodriguez on 21/12/2017.
 */
public class ExceptionBuilder {
	
	private CodigoError codigoError;
    private String message;
    private Throwable rootException;

    private ExceptionBuilder() {
    }

    public static ExceptionBuilder newBuilder() {
        return new ExceptionBuilder();
    }

    public ExceptionBuilder withCode(CodigoError codigoError) {
    	this.codigoError = codigoError;
        return this;
    }
    
    public ExceptionBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ExceptionBuilder withRootException(Throwable rootException) {
        this.rootException = rootException;
        return this;
    }

    public BusinessException buildBusinessException() {
        return new BusinessException(this.message, this.rootException, this.codigoError);
    }

    public SystemException buildSystemException() {
        return new SystemException(this.message, this.rootException, this.codigoError);
    }
}
