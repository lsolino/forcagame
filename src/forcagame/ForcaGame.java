/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forcagame;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.emmemoria.MemoriaTemaRepository;
import br.edu.iff.jogoforca.Aplicacao;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.emmemoria.MemoriaRodadaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dsn2
 */
public class ForcaGame {
    
    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
      System.out.println(message);
    }

    public static void println() {
      System.out.println();
    }

    public static void main(String[] args) throws Exception {
        boolean opcao = true;       
        Scanner scanner = new Scanner(System.in);
        Aplicacao aplicacao = Aplicacao.getSoleInstance();

        JogadorFactory jogadorFactory = aplicacao.getJogadorFactory();
        RodadaFactory rodadaFactory = aplicacao.getRodadaFactory();
        TemaFactory temaFactory = aplicacao.getTemaFactory();

        Tema temaFutebol = temaFactory.getTema("Times de Futebol");
        PalavraFactory palavraFutebolFactory = aplicacao.getPalavraFactory();
        palavraFutebolFactory.getPalavra("flamengo", temaFutebol);
        palavraFutebolFactory.getPalavra("vasco", temaFutebol);
        palavraFutebolFactory.getPalavra("fluminense", temaFutebol);
        
        Tema temaNomes = temaFactory.getTema("Nomes Feminino");
        PalavraFactory palavraNomeFactory = aplicacao.getPalavraFactory();
        palavraNomeFactory.getPalavra("brenda", temaNomes);
        palavraNomeFactory.getPalavra("mariana", temaNomes);
        palavraNomeFactory.getPalavra("yasmin", temaNomes);
        
        
        while (opcao) {
            System.out.println ("######## MENU ########");
            System.out.println ("Seleciona a opção desejada: ");
            System.out.println ("(1) Jogar ");
            System.out.println ("(2) Criar Tema ");
            System.out.println ("(3) Criar Palavra ");
            System.out.println ("(4) Sair ");
            String escolha = scanner.next();
            
            if (escolha.equalsIgnoreCase("4")) {
                opcao = false;
            } else if (escolha.equalsIgnoreCase("3")) {
                System.out.println("Não implementado ainda");
            } else if (escolha.equalsIgnoreCase("2")) {
                System.out.println("Não implementado ainda");
            } else if (escolha.equalsIgnoreCase("1")) {
                System.out.println("Digite seu nome: ");
                String nome = scanner.next();
                Jogador jogador = jogadorFactory.getJogador(nome);
                Rodada rodada = rodadaFactory.getRodada(jogador);
                iniciarPartida(jogador, rodada);
                opcao = false;
            }
        }

        
    }

    private static void iniciarPartida(Jogador jogador, Rodada rodada) {
        Scanner scanner = new Scanner(System.in);
        
        while (!rodada.encerrou()) {
            System.out.println("Jogador: " + jogador.getNome());
            System.out.println("Tema: " + rodada.getTema().getNome());
            System.out.println("Erros: " + rodada.getQtdeErros() + "/" + Rodada.getMaxErros());
            System.out.print("Tentativas: ");
            for (Letra tentativa: rodada.getTentativas()) {
                tentativa.exibir(null);
                System.out.print(" ");
            }
            System.out.println();

            rodada.exibirItens(null);
            System.out.println();
            rodada.exibirBoneco(null);
            System.out.println();

            System.out.print("Digite uma letra (Digite 1 para arriscar): ");
            char codigo = scanner.next().charAt(0);

            if (codigo == '1') {
                List<String> palavras = new ArrayList<>();

                System.out.println("Você decidiu arriscar...");
                for (int i = 1; i <= rodada.getNumPalavras(); i++) {
                  System.out.println("Qual a " + i + "ª palavra? ");
                  String palavra = scanner.next();
                  palavras.add(palavra);
                }

                rodada.arriscar(palavras);
            } else {
                rodada.tentar(codigo);
            }
            System.out.println("");
        }

        if (rodada.descobriu()) {
            System.out.println("Parabéns, você descobriu todas as palavras!");
            rodada.exibirPalavras(null);
        } else {
            System.out.println("ERROU!! Você não descobriu as palavras.");
        }

        System.out.println("Sua pontuação nessa rodada foi: " + rodada.calcularPontos());

        scanner.close();
    }

}
