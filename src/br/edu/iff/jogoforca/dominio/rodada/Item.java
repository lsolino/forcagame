package br.edu.iff.jogoforca.dominio.rodada;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Item extends ObjetoDominioImpl {
    
  private List<Boolean> posicoesDescobertas;
  private String palavraArriscada = null;
  private Palavra palavra;

  static Item criar(int discriminador, Palavra palavra) {
    return new Item(discriminador, palavra);
  }

  public static Item reconstituir(int discriminador, Palavra palavra, List<Integer> posicoesDescobertas, String palavraArriscada) {
    return new Item(discriminador, palavra, posicoesDescobertas, palavraArriscada);
  }

  private Item(int discriminador, Palavra palavra) {
    super(Long.valueOf(discriminador));
    int i;
    this.palavra = palavra;
    this.posicoesDescobertas = new ArrayList<>(palavra.getTamanho());
    for (i = 0; i < palavra.getTamanho(); i++) {
        this.posicoesDescobertas.add(false);
    }
  }

  private Item(int discriminador, Palavra palavra, List<Integer> posicoesDescobertas, String palavraArriscada) {
    super(Long.valueOf(discriminador));
    this.palavra = palavra;
    this.palavraArriscada = palavraArriscada;
    this.posicoesDescobertas = new ArrayList<>(palavra.getTamanho());
    for (Integer i: posicoesDescobertas) {
        this.posicoesDescobertas.add(i, true);
    }
  }

  public Palavra getPalavra() {
    return this.palavra;
  }

  public List<Letra> getLetrasDescobertas() {
    List<Letra> descobertas = new ArrayList<>();

    for (int i = 0; i < posicoesDescobertas.size(); i++) {
      if (posicoesDescobertas.get(i)) {
        descobertas.add(palavra.getLetra(i));
      }
    }

    return descobertas;
  }

  public List<Letra> getLetrasEncobertas() {
    List<Letra> encobertas = new ArrayList<>();

    for (int i = 0; i < posicoesDescobertas.size(); i++) {
      if (!posicoesDescobertas.get(i)) {
        encobertas.add(palavra.getLetra(i));
      }
    }

    return encobertas;
  }

  public int qtdeLetrasEncobertas() {
    return getLetrasEncobertas().size();
  }

  public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta) {
    return qtdeLetrasEncobertas() * valorPorLetraEncoberta;
  }
  
  public boolean descobriu() {
    return acertou() || qtdeLetrasEncobertas() == 0;
  }
  
  public void exibir(Object contexto) {
    palavra.exibir(contexto, posicoesDescobertas);
  }
  
  boolean tentar(char codigo) {
    List<Integer> posicoes = palavra.tentar(codigo);

    if (posicoes.size() == 0)
      return false;
    
    for (Integer posicao: posicoes) {
      posicoesDescobertas.set(posicao, true);
    }

    return true;
  }
  
  void arriscar(String palavra) {
    this.palavraArriscada = palavra;
  }
  
  public String getPalavraArriscada() {
    return this.palavraArriscada;
  }

  public boolean arriscou() {
    return palavraArriscada != null;
  }

  public boolean acertou() {
    return arriscou() ? palavra.comparar(palavraArriscada) : false;
  }
  
}