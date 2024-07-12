package br.com.conversor.modelos;

import com.google.gson.annotations.SerializedName;

public record Moedas(@SerializedName("base_code") String moedaEscolhida,
                        @SerializedName("target_code") String moedaAConverter,
                        @SerializedName("conversion_rate") double valorCotacao) {
}