package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.dominio.ObjetoDominio;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

public class RodadaSorteioFactory extends RodadaFactoryImpl {

  private static RodadaSorteioFactory soleInstance = null;

  public static void createSoleInstance(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
    soleInstance = new RodadaSorteioFactory(repository, temaRepository, palavraRepository);
  }

  public static RodadaSorteioFactory getSoleInstance() {
    if (soleInstance == null) {
      throw new RuntimeException("A fábrica de sorteio da rodada não foi inicializada");
    }

    return soleInstance;
  }

  private RodadaSorteioFactory(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
    super(repository, temaRepository, palavraRepository);
  }

  private <T extends ObjetoDominio> T pickRandom(List<T> pool) {
    Random random = new Random();
    return pool.get(random.nextInt(pool.size()));
  }

  private <T extends ObjetoDominio> List<T> pickMultiple(List<T> pool, int quantity) {
    List<T> palavras = new ArrayList<>();

    while (palavras.size() != quantity) {
      T object = pickRandom(pool);
      if (!palavras.contains(object)) {
        palavras.add(object);
      }
    }

    return palavras;
  }

  @Override
  public Rodada getRodada(Jogador jogador) {
    Tema tema = pickRandom(getTemaRepository().getTodos());
    List<Palavra> palavras = pickMultiple(getPalavraRepository().getPorTema(tema), Rodada.getMaxPalavras());

    Rodada rodada = Rodada.criar(getProximoId(), palavras, jogador);

    try{
      getRodadaRepository().inserir(rodada);
    } catch (RepositoryException repositoryException) {
      throw new RuntimeException("Erro ao salvar rodada no repositório.");
    }

    return rodada;
  }

  
}