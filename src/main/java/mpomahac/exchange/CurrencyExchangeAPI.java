package mpomahac.exchange;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeAPI {

	@GetMapping("/exchanges")
	List<Exchange> getAllExchanges() {
		return Exchange.exchanges;
	}

	@GetMapping("/exchanges/{id}")
	Exchange getExchangeById(@PathVariable long id) {
		return Exchange.getExchangeById(id);
	}

	@GetMapping("/results")
	List<Result> getAllResults() {
		return Result.results;
	}

	@GetMapping("/results/{id}")
	Result getResultByTransactionId(@PathVariable long id) {
		return Result.getResultByTransactionId(id);
	}

	@PostMapping("/exchange")
	Result getCurrencyExchangeResult(@RequestBody Exchange exchange) {
		long start = System.currentTimeMillis();
		float result = ExchangeAPIConnectionHandler.getConversionResult(exchange.getCurrencyFrom(),
				exchange.getCurrencyTo(), exchange.getAmount());
		long timeTaken = System.currentTimeMillis() - start;
		return new Result(result, timeTaken + " ms", exchange.getId());
	}

}
