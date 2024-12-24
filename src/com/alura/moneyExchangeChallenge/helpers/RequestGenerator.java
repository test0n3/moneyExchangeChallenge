package com.alura.moneyExchangeChallenge.helpers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import com.alura.moneyExchangeChallenge.models.Rate;
// info about using .env files in java https://www.twilio.com/en-us/blog/working-with-environment-variables-in-java 

public class RequestGenerator {
  public Rate rateRequest(String baseCode, String targetCode) {
    if (getApiKey().length() == 0) {
      throw new RuntimeException("Api key not found");
    }

    URI address = URI
        .create("https://v6.exchangerate-api.com/v6/" + getApiKey()
            + "/" + "pair/" + baseCode + "/" + targetCode);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(address)
        .build();

    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      return new Gson().fromJson(response.body(), Rate.class);
    } catch (Exception e) {
      throw new RuntimeException("Rate not found");
    }
  }

  private String getApiKey() {
    Dotenv dotenv = null;
    dotenv = Dotenv.configure().load();
    return dotenv.get("EXCHANGE_RATE_API_KEY");
  }
}
