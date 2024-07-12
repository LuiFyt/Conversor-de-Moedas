package br.com.conversor.modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexaoApi {
    private final String base_code;
    private final String target_code;
    private final double amount;

        public ConexaoApi(Moedas valores) {
            this.base_code = valores.moedaEscolhida();
            this.target_code = valores.moedaAConverter();
            this.amount = valores.valorCotacao();
        }

        public String ConsumoApi() {
            String url = "https://v6.exchangerate-api.com/v6/3e1fb13d21bf2120b56ce0e0/pair/" + base_code + "/" + target_code + "/" + amount;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException();
            }
            var json = response.body();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Moedas moedas = gson.fromJson(json, Moedas.class);
            String mensagemUsuario = amount + " " + moedas.moedaEscolhida() + " equivalem a " + moedas.valorCotacao() + " " + moedas.moedaAConverter();
            return mensagemUsuario;
        }
}
