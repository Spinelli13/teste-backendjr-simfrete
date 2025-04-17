package Parte2;

import java.io.*;
import java.util.*;

public class CalculaPreco {

    static class ListaCep {
        String cidade;
        int cepIni;
        int cepFim;

        public ListaCep(String cidade, int inicio, int fim) {
            this.cidade = cidade;
            this.cepIni = inicio;
            this.cepFim = fim;
        }

        public boolean cepVerdadeiro(int cep) {
            return (cep >= cepIni) && (cep <= cepFim);
        }
    }

    static class Rota {
        String destino;
        double custo;

        public Rota(String destino, double custo) {
            this.destino = destino;
            this.custo = custo;
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Comando no terminal para uso: java CalculaPreco.java <arquivo_teste.txt>");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String linha;

            List<ListaCep> faixas = new ArrayList<>();
            // Aqui o código lê faixa de cep e nome de cidade linha por linha
            while ((linha = reader.readLine()) != null && !linha.equals("--")) {
                if (linha.trim().isEmpty())
                    continue; // Ignora as linhas que forem em branco

                String[] partes = linha.split(",");
                if (partes.length != 3)
                    continue; // Ignora linhas que não estejam no formato correto

                try {
                    String cidade = partes[0].trim();
                    int cepInicial = Integer.parseInt(partes[1].trim());
                    int cepFinal = Integer.parseInt(partes[2].trim());
                    faixas.add(new ListaCep(cidade, cepInicial, cepFinal));
                } catch (NumberFormatException e) {
                    // Formato não desejado
                }
            }

            Map<String, List<Rota>> matriz = new HashMap<>();
            // Aqui faz a leitura das rotas no segundo momento do arquivo.
            while ((linha = reader.readLine()) != null && !linha.equals("--")) {
                if (linha.trim().isEmpty())
                    continue; // Ignora as linhas que forem em branco

                String[] partes = linha.split(",");
                if (partes.length != 3)
                    continue; // Ignora formatos de informação não desejados

                String remetente = partes[0].trim();
                String destinatario = partes[1].trim();
                double custo;
                try {
                    custo = Double.parseDouble(partes[2].trim());
                } catch (NumberFormatException e) {
                    continue; // ignora se não for double
                }

                matriz.putIfAbsent(remetente, new ArrayList<>());
                matriz.putIfAbsent(destinatario, new ArrayList<>());
                matriz.get(remetente).add(new Rota(destinatario, custo));
                matriz.get(destinatario).add(new Rota(remetente, custo));
            }

            linha = reader.readLine();
            // Ignora as linhas que forem em branco que vierem após as rotas
            while (linha != null && linha.trim().isEmpty()) {
                linha = reader.readLine(); // Ignora linhas vazias
            }

            // Valida se a linha de pesquisa está certa
            if (linha == null || linha.trim().split(",").length != 2) {
                System.out.println("Cep's para pesquisa inválidos ou não separados por vírgulas.");
                return;
            }

            String[] ceps = linha.trim().split(",");
            int cepOrigem = 0;
            int cepDestino = 0;

            try {
                cepOrigem = Integer.parseInt(ceps[0].trim());
                cepDestino = Integer.parseInt(ceps[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Não foi possível converter os cep's para inteiros: " + e.getMessage());
                return;
            }

            String cidadeOrigem = encontrarCidade(faixas, cepOrigem);
            String cidadeDestino = encontrarCidade(faixas, cepDestino);

            if (cidadeOrigem == null || cidadeDestino == null) {
                System.out.println("Cidade de origem ou destino não encontrada.");
                return;
            }

            Map<String, Double> distancia = new HashMap<>();
            Map<String, String> anterior = new HashMap<>();
            PriorityQueue<String> fila = new PriorityQueue<>(Comparator.comparingDouble(distancia::get));

            // Trata as distancias
            for (String cidade : matriz.keySet()) {
                distancia.put(cidade, Double.POSITIVE_INFINITY);
            }
            distancia.put(cidadeOrigem, 0.0);
            fila.add(cidadeOrigem);

            // Aqui é usado o algoritmo de Dijkstra para encontrarmos o caminho mais curto.
            while (!fila.isEmpty()) {
                String atual = fila.poll();

                for (Rota rota : matriz.getOrDefault(atual, new ArrayList<>())) {
                    double novaDistancia = distancia.get(atual) + rota.custo;
                    if (novaDistancia < distancia.get(rota.destino)) {
                        distancia.put(rota.destino, novaDistancia);
                        anterior.put(rota.destino, atual);
                        fila.add(rota.destino);
                    }
                }
            }

            // Aqui reconstruimos a rota mais curta para devolver ao usuario.
            List<String> caminho = new ArrayList<>();
            String atual = cidadeDestino;
            while (atual != null) {
                caminho.add(atual);
                atual = anterior.get(atual);
            }
            Collections.reverse(caminho);

            System.out.println("Rota a ser realizada: " + String.join(" -> ", caminho));
            System.out.printf("Preço frete: %.2f\n", distancia.get(cidadeDestino));

        } catch (IOException e) {
            System.err.println("Não foi possível ler o arquivo: " + e.getMessage());
        }
    }

    // Função para achar a cidade correspondente ao CEP
    public static String encontrarCidade(List<ListaCep> faixas, int cep) {
        ListaCep resultado = null;
        for (ListaCep faixa : faixas) {
            if (faixa.cepVerdadeiro(cep)) {
                if (resultado == null || (faixa.cepFim - faixa.cepIni) < (resultado.cepFim - resultado.cepIni)) {
                    resultado = faixa;
                }
            }
        }
        return resultado != null ? resultado.cidade : null;
    }
}
