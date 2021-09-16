package mpomahac.exceptions;

public class InvalidCurrencyException extends RuntimeException {

	public InvalidCurrencyException(String currency) {
		super("The currency " + currency
				+ " is not supported or doesn't exist. Please check the spelling or enter a valid currency!");
	}

}
