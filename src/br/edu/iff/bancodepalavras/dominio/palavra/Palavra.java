package br.edu.iff.bancodepalavras.dominio.palavra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Palavra extends ObjetoDominioImpl {

  private static LetraFactory letraFactory;
  private List<Letra> palavras;
  private Tema tema;

  public static void setLetraFactory(LetraFactory factory) {
    letraFactory = factory;
  }

  public static LetraFactory getLetraFactory() {
    return letraFactory;
  }

  public static Palavra criar(Long id, String palavra, Tema tema) {
    return new Palavra(id, palavra, tema);
  }

  public static Palavra reconstituir(Long id, String palavra, Tema tema) {
    return new Palavra(id, palavra, tema);
  }

  private Palavra(Long id, String palavra, Tema tema) {
      super(id);
      LetraFactory letraFactory = getLetraFactory();

      if (letraFactory == null) {
        throw new RuntimeException("Fábrica de Letra não inicializada");
      }

      this.tema = tema;
      this.palavras = new ArrayList<>();

      for (int i = 0; i < palavra.length(); i++) {
        this.palavras.add(letraFactory.getLetra(palavra.charAt(i)));
      }
  }

  public List<Letra> getLetras() {
    return Collections.unmodifiableList(this.palavras);
  }

  public Letra getLetra(int posicao) {
    return this.palavras.get(posicao);
  }

  public void exibir(Object contexto) {
    for (Letra letra: palavras) {
      letra.exibir(null);
    }
  }

  public void exibir(Object contexto, List<Boolean> posicoes) {
    if (posicoes.size() != palavras.size()) {
      throw new RuntimeException("Erro ao exibir palavra!");
    }

    LetraFactory letraFactory = getLetraFactory();
    Letra letraEncoberta = letraFactory.getLetraEncoberta();

    for (int i = 0; i < palavras.size(); i++) {
      if (posicoes.get(i)) {
        palavras.get(i).exibir(null);
      } else {
        letraEncoberta.exibir(null);
      }
    }
  }

  public List<Integer> tentar(char codigo) {
    List<Integer> posicoesEncontradas = new ArrayList<>();

    for (int i = 0; i < palavras.size(); i++) {
      if (palavras.get(i).getCodigo() == codigo) {
        posicoesEncontradas.add(i);
      }
    }
    return posicoesEncontradas;
  }

  public Tema getTema() {
    return this.tema;
  }

  public boolean comparar(String palavra) {
    for (int i = 0; i < this.palavras.size(); i++) {
      if (this.palavras.get(i).getCodigo() != palavra.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  public int getTamanho() {
    return this.palavras.size();
  }

  @Override
  public String toString() {
    return "Palavra: " + palavras + " - Tema: " + tema;
  }
}