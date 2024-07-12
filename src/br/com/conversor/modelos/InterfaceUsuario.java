package br.com.conversor.modelos;

import java.util.Scanner;

public class InterfaceUsuario {
    boolean ativador = true;
    Scanner leitura = new Scanner(System.in);
    public void menu() {
        while (ativador) {
            System.out.println("""
                    ******************************************************
                    Seja bem vindo ao Conversor de Moedas

                    Escolha a moeda que deseja converter ou saia do programa:

                    1) Dólar ==> Peso argentino
                    2) Peso argentino ==> Dolár
                    3) Dólar ==> Real brasileiro
                    4) Real brasileiro ==> Dólar
                    5) Dólar ==> Peso colombiano
                    6) Peso colombiano ==> Dólar
                    7) Sair
                    ******************************************************
                    """);
            String escolha = leitura.nextLine();

            switch (escolha) {
                case "1":
                    this.converte("USD", "ARS");
                    break;
                case "2":
                    this.converte("ARS", "USD");
                    break;
                case "3":
                    this.converte("USD", "BRL");
                    break;
                case "4":
                    this.converte("BRL", "USD");
                    break;
                case "5":
                    this.converte("USD", "COP");
                    break;
                case "6":
                    this.converte("COP", "USD");
                    break;
                default:
                    if (escolha.equals("7")) {
                        System.out.println("Saindo...");
                        ativador = false;
                    } else {
                        System.out.println("Opção Inválida! " +
                                "Selecione uma opção valida. ");
                    }
                    break;
            }
        }
    }
    private void converte (String moedaInicial, String moedaFinal){
        System.out.println("Digite o valor a ser convertido: ");

        try {
            double valor = leitura.nextDouble();
            leitura.nextLine();
            Moedas moedas = new Moedas(moedaInicial, moedaFinal, valor);

            ConexaoApi conexaoApi = new ConexaoApi(moedas);
            System.out.println(conexaoApi.ConsumoApi());

        } catch (Exception e) {
            System.out.println("Valor digitado inválido, Tente novamente!");
        }
    }
}
