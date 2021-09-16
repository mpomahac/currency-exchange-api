package mpomahac.exchange;

import java.util.ArrayList;
import java.util.List;

import mpomahac.exceptions.ResultNotFoundException;

public class Result {

	public static List<Result> results = new ArrayList<Result>();

	private float conversionResult;
	private String timeTaken;
	private long exchangeId;

	public Result(float conversionResult, String timeTaken, long exchangeId) {
		this.conversionResult = conversionResult;
		this.timeTaken = timeTaken;
		this.setExchangeId(exchangeId);
		results.add(this);
	}

	public float getConversionResult() {
		return conversionResult;
	}

	public void setConversionResult(int conversionResult) {
		this.conversionResult = conversionResult;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	public long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public static Result getResultByTransactionId(long id) {
		return results.stream().filter(r -> r.getExchangeId() == id).findFirst()
				.orElseThrow(() -> new ResultNotFoundException(id));
	}

}
