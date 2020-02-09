package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {
  
  private static JogadorFactoryImpl soleInstance = null;

  public static void createSoleInstance(JogadorRepository repository) {
    soleInstance = new JogadorFactoryImpl(repository);
  }

  public static JogadorFactoryImpl getSoleInstance() {
    if (soleInstance == null) {
      throw new RuntimeException("Fábrica de jogador ainda não foi inicializada");
    }

    return soleInstance;
  }

  private JogadorFactoryImpl(JogadorRepository repository) {
    super(repository);
  }

  private JogadorRepository getJogadorRepository() {
    return (JogadorRepository) getRepository();
  }

  @Override
  public Jogador getJogador(String nome) {
    Jogador jogador = Jogador.criar(getProximoId(), nome);

    try {
      getJogadorRepository().inserir(jogador);
    } catch (RepositoryException repositoryException) {
      throw new RuntimeException("Erro ao salvar jogador no repositório");
    }

    return jogador;
  }
}