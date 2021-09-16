package mpomahac.exchange;

import java.util.ArrayList;
import java.util.List;

import mpomahac.exceptions.ExchangeNotFoundException;

public class Exchange {

	public static List<Exchange> exchanges = new ArrayList<Exchange>();

	private long id;
	private String currencyFrom;
	private String currencyTo;
	private int amount;

	public Exchange() {
		this.id = exchanges.size();
		exchanges.add(this);
	};

	public Exchange(String currencyFrom, String currencyTo, int amount) {
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amount = amount;
		this.id = exchanges.size();
		exchanges.add(this);
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ID: " + this.id + " | " + this.amount + " " + this.currencyFrom + " -> " + this.currencyTo;
	}

	public static Exchange getExchangeById(long id) {
		return exchanges.stream().filter(t -> t.getId() == id).findFirst()
				.orElseThrow(() -> new ExchangeNotFoundException(id));
	}

}
