package br.edu.iff.jogoforca.dominio.rodada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public class Rodada extends ObjetoDominioImpl {
  private static int maxPalavras = 3;
  private static int maxErros = 10;
  private static int pontosQuandoDescobreTodasAsPalavras = 100;
  private static int pontosPorLetraEncoberta = 15;
  private static BonecoFactory bonecoFactory = null;
  private List<Item> itens;
  private Jogador jogador;
  private Boneco boneco;
  private Set<Letra> letrasCertas;
  private Set<Letra> erradas;

  public static int getMaxPalavras() {
    return maxPalavras;
  }

  public static void setMaxPalavras(int max) {
    maxPalavras = max;
  }

  public static int getMaxErros() {
    return maxErros;
  }

  public static void setMaxErros(int max) {
    maxErros = max;
  }

  public static int getPontosQuandoDescobreTodasAsPalavras() {
    return pontosQuandoDescobreTodasAsPalavras;
  }

  public static void setPontosQuandoDescobreTodasAsPalavras(int pontos) {
    pontosQuandoDescobreTodasAsPalavras = pontos;
  }

  public static int getPontosPorLetraEncoberta() {
    return pontosPorLetraEncoberta;
  }

  public static void setPontosPorLetraEncoberta(int pontos) {
    pontosPorLetraEncoberta = pontos;
  }

  public static BonecoFactory getBonecoFactory() {
    return bonecoFactory;
  }

  public static void setBonecoFactory(BonecoFactory factory) {
    bonecoFactory = factory;
  }

  public static Rodada criar(Long id, List<Palavra> palavras, Jogador jogador) {
    return new Rodada(id, palavras, jogador);
  }

  public static Rodada reconstituir(Long id, List<Item> itens, List<Letra> erradas, Jogador jogador) {
    return new Rodada(id, itens, erradas, jogador);
  }
  
  private Rodada(Long id, List<Palavra> palavras, Jogador jogador) {
    super(id);

    if (getBonecoFactory() == null) {
      throw new RuntimeException("Fábrica do boneco ainda não foi inicializada");
    }

    boneco = getBonecoFactory().getBoneco();

    this.jogador = jogador;
    this.letrasCertas = new HashSet<>();
    this.erradas = new HashSet<>();
    this.itens = new ArrayList<>(palavras.size());

    for (int i = 0; i < palavras.size(); i++) {
      this.itens.add(Item.criar(i, palavras.get(i)));
    }
  }

  private Rodada(Long id, List<Item> itens, List<Letra> erradas, Jogador jogador) {
    super(id);

    if (getBonecoFactory() == null) {
      throw new RuntimeException("Fábrica do boneco ainda não foi inicializada");
    }

    boneco = getBonecoFactory().getBoneco();

    this.jogador = jogador;
    this.letrasCertas = new HashSet<>();
    this.erradas = new HashSet<>();
    this.itens = new ArrayList<>(itens.size());

    for (int i = 0; i < itens.size(); i++) {
      Item item = itens.get(i);
      this.itens.add(item);

      for (Letra correta: item.getLetrasDescobertas()) {
        this.letrasCertas.add(correta);
      }
    }

    for (Letra errada: erradas) {
      this.erradas.add(errada);
    }
  }

  public Jogador getJogador() {
    return this.jogador;
  }

  public Tema getTema() {
    return this.itens.get(0).getPalavra().getTema();
  }

  public List<Palavra> getPalavras() {
    List<Palavra> palavras = new ArrayList<>();
    for (Item i: itens) {
      palavras.add(i.getPalavra());
    }
    return palavras;
  }

  public int getNumPalavras() {
    return itens.size();
  }
  
  public void tentar(char codigo) {
    if (encerrou()) {
      return;
    }
      
    Map<Item, Boolean> itensAcertados = new HashMap<>();
    LetraFactory letraFactory = Palavra.getLetraFactory();

    for (Item item: itens) {
      if (item.tentar(codigo)) {
        letrasCertas.add(letraFactory.getLetra(codigo));
        itensAcertados.put(item, true);
      } else {
          itensAcertados.put(item, false);
      }
    }

    if (!itensAcertados.containsValue(true)) {
      erradas.add(letraFactory.getLetra(codigo));
    }

    if (encerrou()) {
        this.jogador.setPontuacao(this.calcularPontos());
    }
      
  }
  
  public void arriscar(List<String> palavras) {
    if (encerrou()) {
      return;
    }
    
    int aux = 0;
    
    if (palavras.size() < itens.size()) {
        aux = palavras.size();
    } else {
        aux = itens.size();
    }

    for (int i = 0; i < aux; i++) {
      itens.get(i).arriscar(palavras.get(i));
    }

    if (encerrou()) {
        this.jogador.setPontuacao(this.calcularPontos());
    }
  }

  public void exibirItens(Object contexto) {
    for (Item item: itens) {
      item.exibir(contexto);
      System.out.println();
    }
  }

  public void exibirBoneco(Object contexto) {
    boneco.exibir(contexto, getQtdeErros());
  }

  public void exibirPalavras(Object contexto) {
    for (Palavra palavra: getPalavras()) {
      palavra.exibir(contexto);
      System.out.println();
    }
  }

  public Set<Letra> getTentativas() {
    Set<Letra> tentativas = new HashSet<>();
    tentativas.addAll(letrasCertas);
    tentativas.addAll(erradas);

    return tentativas;
  }

  public Set<Letra> getCertas() {
    return Collections.unmodifiableSet(letrasCertas);
  }

  public Set<Letra> getErradas() {
    return Collections.unmodifiableSet(erradas);
  }

  public int calcularPontos() {
    if (!descobriu()) {
      return 0;
    }
      
    int pontos = getPontosQuandoDescobreTodasAsPalavras();

    for (Item item: itens) {
      pontos = pontos + item.calcularPontosLetrasEncobertas(getPontosPorLetraEncoberta());
    }

    return pontos;
  }
  
  public boolean encerrou() {
    if (arriscou() || descobriu() || getQtdeTentativaRestantes() == 0) {
      return true;
    }
      
    return false;
  }
  
  public boolean descobriu() {
    for (Item item: itens) {
      if (!item.descobriu()) {
        return false;
      }
    }
      
    return true;
  }

  public boolean arriscou() {
    for (Item item: itens) {
      if (item.arriscou()) {
        return true;
      }
    }
      
    return false;
  }
  
  public int getQtdeTentativaRestantes() {
    return getMaxErros() - erradas.size();
  }

  public int getQtdeErros() {
    return erradas.size();
  }

  public int getQtdeAcertos() {
    return letrasCertas.size();
  }

  public int getQtdeTentativas() {
    return getQtdeErros() + getQtdeAcertos();
  }
  
}