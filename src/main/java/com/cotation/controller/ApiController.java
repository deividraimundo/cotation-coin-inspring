package com.cotation.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cotation.controller.coin.Currency;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class ApiController {	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@GetMapping(value = "/callcotationcoin")
	private String GetCurrencies() {
		String url = "https://economia.awesomeapi.com.br/last/USD-BRL,EUR-BRL,BTC-BRL";
		RestTemplate restTemplate = new RestTemplate();
		Map jsonObject = restTemplate.getForObject(url, Map.class);
		Map USDBRL = (Map) jsonObject.get("USDBRL");
		Map EURBRL = (Map) jsonObject.get("EURBRL");
		Map BTCBRL = (Map) jsonObject.get("BTCBRL");
		
		Currency currency = new Currency();
		currency.setBidDollar(USDBRL.get("bid").toString());
		currency.setBidEuro(EURBRL.get("bid").toString());
		currency.setBidBitcoin(BTCBRL.get("bid").toString());
		
		return "Dollar: " + currency.getBidDollar().toString() + "\nEuro: " + currency.getBidEuro().toString() + "\nBitcoin: " + currency.getBidBitcoin().toString();
	}
}