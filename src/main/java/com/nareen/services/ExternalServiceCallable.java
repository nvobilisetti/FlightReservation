package com.nareen.services;

import java.util.concurrent.Callable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ExternalServiceCallable implements Callable<String> {
	private static final Logger log = LogManager.getLogger(ExternalServiceCallable.class);
	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/myCardservice/rest/PaymentService";
	private static final String SUCCESS_RESULT = "<response>SUCCESS</response>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";

	String userName;
	String cardDetails;
	String cardCvv;
	String cardExp;
	long price;
	
	public ExternalServiceCallable(String userName, String cardDetails, String cardCvv, String cardExp, long price) {
		this.userName = userName;
		this.cardDetails = cardDetails;
		this.cardCvv = cardCvv;
		this.cardExp = cardExp;
		this.price = price;
		this.client = ClientBuilder.newClient();
	}

	@Override
	public String call() throws Exception {

		String result = null;
		String totalPrice = Long.toString(price);
		Form form = new Form();
		form.param("userName", userName);
		form.param("cardDetails", cardDetails);
		form.param("cardCvv", cardCvv);
		form.param("cardExp", cardExp);
		form.param("price", totalPrice);

		String callResult = client.target(REST_SERVICE_URL).
				path("/processpayment").
				request(MediaType.APPLICATION_XML)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		result = PASS;
		log.info("callResult String " + callResult);
		if (!SUCCESS_RESULT.equals(callResult)) {
			log.info("failed to match the string");
			result = FAIL;
		}
		Thread.sleep(1000*5);
		log.info("result :"+result);
		return result;
	}

}
