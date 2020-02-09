package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {

  private static PalavraFactoryImpl soleInstance = null;

  public static PalavraFactoryImpl getSoleInstance() {
    if (soleInstance == null) {
      throw new RuntimeException("Fábrica de palavra não inicializada");
    }

    return soleInstance;
  }

  public static void createSoleInstance(PalavraRepository repository) {
    soleInstance = new PalavraFactoryImpl(repository);
  }

  private PalavraFactoryImpl(PalavraRepository repository) {
    super(repository);
  }

  private PalavraRepository getPalavraRepository() {
    return (PalavraRepository) getRepository();
  }

  @Override
  public Palavra getPalavra(String palavra, Tema tema) {
    Palavra auxPalavra = Palavra.criar(getProximoId(), palavra, tema);

    try {
      getPalavraRepository().inserir(auxPalavra);
    } catch (RepositoryException repositoryException) {
      throw new RuntimeException("Erro ao salvar palavra no repositório");
    }

    return auxPalavra;
  }
  
}