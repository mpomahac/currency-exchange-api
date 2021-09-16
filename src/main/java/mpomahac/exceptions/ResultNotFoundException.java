package mpomahac.exceptions;

public class ResultNotFoundException extends RuntimeException {

	public ResultNotFoundException(long id) {
		super("There is no result for exchange with ID " + id + " or the exchange doesn't exist.");
	}

}
