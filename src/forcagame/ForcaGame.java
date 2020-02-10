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
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
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
import javax.swing.JOptionPane;

/**
 *
 * @author dsn2
 */
public class ForcaGame {
    
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
        palavraFutebolFactory.getPalavra("botafogo", temaFutebol);
        palavraFutebolFactory.getPalavra("goytacaz", temaFutebol);
        
        Tema temaFS = temaFactory.getTema("Filmes e Séries");
        PalavraFactory palavraFSFactory = aplicacao.getPalavraFactory();
        palavraFSFactory.getPalavra("atypical", temaFS);
        palavraFSFactory.getPalavra("suits", temaFS);
        palavraFSFactory.getPalavra("friends", temaFS);
        palavraFSFactory.getPalavra("coringa", temaFS);
        palavraFSFactory.getPalavra("frozen", temaFS);
        
        Tema temaNomes = temaFactory.getTema("Nomes Feminino");
        PalavraFactory palavraNomeFactory = aplicacao.getPalavraFactory();
        palavraNomeFactory.getPalavra("brenda", temaNomes);
        palavraNomeFactory.getPalavra("mariana", temaNomes);
        palavraNomeFactory.getPalavra("yasmin", temaNomes);
        palavraNomeFactory.getPalavra("aurea", temaNomes);
        palavraNomeFactory.getPalavra("jubileia", temaNomes);
        
        Tema temaGeral = temaFactory.getTema("Geral");
        PalavraFactory palavraGeralFactory = aplicacao.getPalavraFactory();
        palavraGeralFactory.getPalavra("flamengo", temaGeral);
        palavraGeralFactory.getPalavra("vasco", temaGeral);
        palavraGeralFactory.getPalavra("fluminense", temaGeral);
        palavraGeralFactory.getPalavra("botafogo", temaGeral);
        palavraGeralFactory.getPalavra("goytacaz", temaGeral);
        palavraGeralFactory.getPalavra("brenda", temaGeral);
        palavraGeralFactory.getPalavra("mariana", temaGeral);
        palavraGeralFactory.getPalavra("yasmin", temaGeral);
        palavraGeralFactory.getPalavra("aurea", temaGeral);
        palavraGeralFactory.getPalavra("jubileia", temaGeral);
        palavraGeralFactory.getPalavra("atypical", temaGeral);
        palavraGeralFactory.getPalavra("suits", temaGeral);
        palavraGeralFactory.getPalavra("friends", temaGeral);
        palavraGeralFactory.getPalavra("coringa", temaGeral);
        palavraGeralFactory.getPalavra("frozen", temaGeral);
        
        while (opcao) {
            System.out.println ("######## MENU ########");
            System.out.println ("Seleciona a opção desejada: ");
            System.out.println ("(1) Jogar ");
            System.out.println ("(2) Criar Tema ");
            System.out.println ("(3) Criar Palavra ");
            System.out.println ("(4) Sair ");
            String escolha = scanner.next();
            
            if (escolha.equalsIgnoreCase("4")) {
                System.out.println("Obrigado por jogar!");
                System.out.println("Grupo: Luan Soliño, Lucas Diniz e Matheus Melo");
                opcao = false;
            } else if (escolha.equalsIgnoreCase("3")) {
            	throw new RuntimeException("Função ainda não implementada!");
            } else if (escolha.equalsIgnoreCase("2")) {
            	throw new RuntimeException("Função ainda não implementada!");
            } else if (escolha.equalsIgnoreCase("1")) {
                System.out.println("Digite seu nome: ");
                String nome = scanner.next();
                Jogador jogador = jogadorFactory.getJogador(nome);
                Rodada rodada = rodadaFactory.getRodada(jogador);
                iniciarPartida(jogador, rodada);
                opcao = false;
            } else {
                JOptionPane.showMessageDialog(null, "Opção Inválida!!! Saindo do jogo...");
                System.out.println("Obrigado por jogar!");
                System.out.println("Grupo: Luan Soliño, Lucas Diniz e Matheus Melo");
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

            if (codigo >= 'A' && codigo <= 'Z') {
            	codigo = (char)(codigo+32);
            } else if (codigo == '1') {
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
