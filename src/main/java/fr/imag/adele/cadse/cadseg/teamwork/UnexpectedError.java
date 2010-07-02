package fr.imag.adele.cadse.cadseg.teamwork;

/**
 * Represents an error which is related to runtime errors.
 * 
 * @author Thomas
 *
 */
public class UnexpectedError {

	private String _msg;
	private Throwable _exception;
	
	public UnexpectedError(String errorMessage, Throwable errorException) {
		_msg = errorMessage;
		_exception = errorException;
	}
	
	public String getMessage() {
		return _msg;
	}

	public void setErrorMessage(String msg) {
		_msg = msg;
	}

	public Throwable getException() {
		return _exception;
	}

	public void setException(Throwable exception) {
		_exception = exception;
	}
}
