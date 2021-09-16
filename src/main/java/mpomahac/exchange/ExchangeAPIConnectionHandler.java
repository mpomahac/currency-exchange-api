package mpomahac.exchange;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import mpomahac.exceptions.InvalidCurrencyException;

public class ExchangeAPIConnectionHandler {

	private static final String API_ACCESS_KEY = "8911422fb3341bf23954800bb73bd262";
	private static final String API_BASE_URL = "http://api.exchangeratesapi.io/v1/";

	public static float getConversionResult(String currencyFrom, String currencyTo, int amount) {
		RestTemplate restTemplate = new RestTemplate();
		String request = API_BASE_URL + "latest?access_key=" + API_ACCESS_KEY + "&base=EUR"; // Base currency had to be
																								// hardcoded due to a
																								// "base_currency_access_restricted"
																								// error from API
		Object response = restTemplate.getForObject(request, Object.class);
		Map<String, Float> exchangeRates = mapValues(response.toString());
		if (!exchangeRates.containsKey(currencyFrom)) {
			throw new InvalidCurrencyException(currencyFrom);
		} else if (!exchangeRates.containsKey(currencyTo)) {
			throw new InvalidCurrencyException(currencyTo);
		}

		return (float) amount / exchangeRates.get(currencyFrom).floatValue()
				* exchangeRates.get(currencyTo).floatValue();
	}

	private static Map<String, Float> mapValues(String values) {
		Map<String, Float> exchangeRates = new HashMap<String, Float>();
		values = values.split("rates=")[1].replaceAll("\\{|\\}| ", "");
		for (String s : values.split("\\,")) {
			String currency = s.split("=")[0];
			Float value = Float.valueOf(s.split("=")[1]);
			exchangeRates.put(currency, value);
		}
		return exchangeRates;
	}

}
