package mpomahac.exchange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import mpomahac.exceptions.ExchangeNotFoundException;
import mpomahac.exceptions.InvalidCurrencyException;
import mpomahac.exceptions.ResultNotFoundException;

public class APITest {

	@Test
	void invalidCurrencyExceptionTests() {
		Throwable e = assertThrows(InvalidCurrencyException.class,
				() -> ExchangeAPIConnectionHandler.getConversionResult("EUR", "EU", 1));
		assertEquals(
				"The currency EU is not supported or doesn't exist. Please check the spelling or enter a valid currency!",
				e.getMessage());

		e = assertThrows(InvalidCurrencyException.class,
				() -> ExchangeAPIConnectionHandler.getConversionResult("US", "EUR", 1));
		assertEquals(
				"The currency US is not supported or doesn't exist. Please check the spelling or enter a valid currency!",
				e.getMessage());
	}

	@Test
	void exchangeNotFoundExceptionTest() {
		for (int i = 0; i < 10; i++) {
			new Exchange("EUR", "USD", (i + 1) * 10);
		}

		assertNotEquals(0, Exchange.exchanges.size());
		assertEquals(10, Exchange.exchanges.size());

		Throwable e = assertThrows(ExchangeNotFoundException.class, () -> Exchange.getExchangeById(100));
		assertEquals("There is no exchange with ID 100!", e.getMessage());

		assertEquals("ID: 5 | 60 EUR -> USD", Exchange.getExchangeById(5).toString());
	}

	@Test
	void resultNotFoundExceptionTest() {
		new Result(15f, "", 1);

		Throwable e = assertThrows(ResultNotFoundException.class, () -> Result.getResultByTransactionId(2));
		assertEquals("There is no result for exchange with ID 2 or the exchange doesn't exist.", e.getMessage());
	}

}
