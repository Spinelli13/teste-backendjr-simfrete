package Parte1;

import java.io.*;
import java.util.*;

public class BuscaCidade {

    static class ListaCep {
        String cidade;
        int cepIni;
        int cepFim;

        public ListaCep(String cidade, int cepIni, int cepFim) {
            this.cidade = cidade;
            this.cepIni = cepIni;
            this.cepFim = cepFim;
        }

        public boolean cepVerdadeiro(int cep) {
            return (cep >= cepIni) && (cep <= cepFim);
        }

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Comando no terminal para uso: java BuscaCidade.java <arquivo_teste.txt>");
            return;
        }

        String doc = args[0];
        List<ListaCep> listaCep = new ArrayList<>();
        int cepConsulta = -1;
        boolean testeLinha = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(doc))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty())
                    continue;

                if (linha.equals("--")) {
                    testeLinha = false;
                    continue;
                }

                if (testeLinha) {
                    String[] index = linha.split(",");
                    if (index.length != 3)
                        continue;

                    try {
                        String cidade = index[0].trim();
                        int cepInicial = Integer.parseInt(index[1].trim());
                        int cepFinal = Integer.parseInt(index[2].trim());

                        if (cepInicial <= cepFinal) {
                            listaCep.add(new ListaCep(cidade, cepInicial, cepFinal));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro na faixa: " + linha);
                    }

                } else {
                    if (linha.length() <= 8 && linha.matches("\\d+")) {
                        try {
                            cepConsulta = Integer.parseInt(linha);
                        } catch (NumberFormatException e) {
                            System.out.println("CEP inválido: " + linha);
                        }
                        break;
                    } else {
                        System.out.println("Formato de CEP inválido: " + linha);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Arquivo Inválido: " + e.getMessage());
            return;
        }

        if (cepConsulta == -1) {
            System.out.println("CEP de consulta não encontrado ou inválido.");
            return;
        }

        ListaCep linhaResultado = null;

        for (ListaCep faixa : listaCep) {
            if (faixa.cepVerdadeiro(cepConsulta)) {
                if (linhaResultado == null
                        || (faixa.cepFim - faixa.cepIni) < (linhaResultado.cepFim - linhaResultado.cepIni)) {
                    linhaResultado = faixa;
                }

            }
        }

        if (linhaResultado != null) {
            System.out.println(linhaResultado.cidade);
        } else {
            System.out.println("CEP não encontrado.");
        }
    }
}
