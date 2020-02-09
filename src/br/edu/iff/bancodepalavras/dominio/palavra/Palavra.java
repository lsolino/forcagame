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

  private List<Letra> palavra;
  private Tema tema;

  private Palavra(Long id, String palavra, Tema tema) {
      super(id);
      LetraFactory factory = getLetraFactory();

      if (factory == null) {
        throw new RuntimeException("Fábrica de Letra não inicializada");
      }

      this.tema = tema;
      this.palavra = new ArrayList<>();

      for (int i = 0; i < palavra.length(); i++) {
        this.palavra.add(factory.getLetra(palavra.charAt(i)));
      }
  }

  public List<Letra> getLetras() {
    return Collections.unmodifiableList(this.palavra);
  }

  public Letra getLetra(int posicao) {
    return this.palavra.get(posicao);
  }

  public void exibir(Object contexto) {
    for (Letra letra: palavra) {
      letra.exibir(null);
    }
  }

  public void exibir(Object contexto, List<Boolean> posicoes) {
    if (posicoes.size() != palavra.size()) {
      throw new RuntimeException("posicoes precisa ter o mesmo tamanho da palavra");
    }

    LetraFactory factory = getLetraFactory();
    Letra letraEncoberta = factory.getLetraEncoberta();

    for (int i = 0; i < palavra.size(); i++) {
      if (posicoes.get(i)) {
        palavra.get(i).exibir(null);
      } else {
        letraEncoberta.exibir(null);
      }
    }
  }

  public List<Integer> tentar(char codigo) {
    List<Integer> posicoesEncontradas = new ArrayList<>();

    for (int i = 0; i < palavra.size(); i++) {
      if (palavra.get(i).getCodigo() == codigo) {
        posicoesEncontradas.add(i);
      }
    }
    return posicoesEncontradas;
  }

  public Tema getTema() {
    return this.tema;
  }

  public boolean comparar(String palavra) {
    for (int i = 0; i < this.palavra.size(); i++) {
      if (this.palavra.get(i).getCodigo() != palavra.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  public int getTamanho() {
    return this.palavra.size();
  }

  @Override
  public String toString() {
    return "Palavra: " + palavra + " - Tema: " + tema;
  }
}