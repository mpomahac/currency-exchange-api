package mpomahac.exceptions;

public class ExchangeNotFoundException extends RuntimeException {
	
	public ExchangeNotFoundException(long id){
		super("There is no exchange with ID " + id + "!");
	}

}
