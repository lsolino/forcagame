package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaTemaRepository implements TemaRepository {

  private static MemoriaTemaRepository soleInstance = null;

  public static MemoriaTemaRepository getSoleInstance() {
      if (soleInstance == null) {
        soleInstance = new MemoriaTemaRepository();
      }

      return soleInstance;
  }

  private List<Tema> pool;

  private MemoriaTemaRepository() {
    pool = new ArrayList<>();
  }

  @Override
  public long getProximoId() {
    return pool.size() + 1;
  }

  @Override
  public Tema getPorId(Long id) {    

    for (Tema tema: pool) {
      if (tema.getId().equals(id)) {
        return tema;
      } 
    }  
    
    throw new RuntimeException("Não foi encontrado nenhum tema com o ID = " + id);
  }

  @Override
  public List<Tema> getPorNome(String nome) {

    List<Tema> temas = new ArrayList<>();

    for (Tema tema: pool) {
      String auxTema = tema.getNome().toLowerCase();
      String auxNome = nome.toLowerCase();
      if (auxTema.contains(auxNome)) {
        temas.add(tema);
      }
    }

    return temas;
  }

  @Override
  public List<Tema> getTodos() {
    return Collections.unmodifiableList(pool);
  }

  @Override
  public void inserir(Tema tema) throws RepositoryException {
    
    if (pool.contains(tema)) {
      throw new RepositoryException("O tema " + tema + " já está salvo no repositório.");
    } else {
      pool.add(tema);
    }

  }

  @Override
  public void atualizar(Tema tema) throws RepositoryException {
    //TODO
  }

  @Override
  public void remover(Tema tema) throws RepositoryException {
    // TODO 
  }

  
}