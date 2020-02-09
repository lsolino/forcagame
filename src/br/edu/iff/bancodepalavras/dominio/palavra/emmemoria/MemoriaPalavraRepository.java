package br.edu.iff.bancodepalavras.dominio.palavra.emmemoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;

public class MemoriaPalavraRepository implements PalavraRepository {

  private static MemoriaPalavraRepository soleInstance = null;

  public static MemoriaPalavraRepository getSoleInstance() {
    if (soleInstance == null) {
      soleInstance = new MemoriaPalavraRepository();
    }

    return soleInstance;
  }

  private List<Palavra> pool;

  private MemoriaPalavraRepository() {
    pool = new ArrayList<>();
  }

  @Override
  public long getProximoId() {
    return pool.size() + 1;
  }

  @Override
  public Palavra getPorId(long id) {
    for (Palavra palavra: pool) {
      if (palavra.getId().equals(id)) {
        return palavra;
      }
    }

    throw new RuntimeException("Não foi possível encontra nenhuma palavra com o ID: " + id);
  }

  @Override
  public List<Palavra> getPorTema(Tema tema) {
    List<Palavra> palavras = new ArrayList<>();

    for (Palavra palavra: pool) {
      if (palavra.getTema() == tema) {
        palavras.add(palavra);
      }
    }

    return palavras;
  }

  @Override
  public List<Palavra> getTodas() {
    return Collections.unmodifiableList(pool);
  }

  @Override
  public Palavra getPalavra(String palavra) {
    for (Palavra auxPalavra: pool) {
      if (auxPalavra.comparar(palavra)) {
        return auxPalavra;
      }
    }

    throw new RuntimeException("Não foi possível encontrar a palavra: " + palavra);
  }

  @Override
  public void inserir(Palavra palavra) throws RepositoryException {
    if (pool.contains(palavra)) {
      throw new RepositoryException("Palavra já está salvo no repositório");
    }
    pool.add(palavra);
  }

  @Override
  public void atualizar(Palavra palavra) throws RepositoryException {
    this.remover(palavra);
    this.inserir(palavra);
  }

  @Override
  public void remover(Palavra palavra) throws RepositoryException {
    if (pool.contains(palavra)) {
        pool.remove(palavra);
    } else {
        throw new RepositoryException("A palavra " + palavra + "não está presente no repositório");
    }
  }
}