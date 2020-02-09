package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaRodadaRepository implements RodadaRepository {

  private static MemoriaRodadaRepository soleInstance = null;

  public static MemoriaRodadaRepository getSoleInstance() {
    if (soleInstance == null) {
      soleInstance = new MemoriaRodadaRepository();
    }

    return soleInstance;
  }

  private List<Rodada> pool;

  private MemoriaRodadaRepository() {
    pool = new ArrayList<>();
  }

  @Override
  public long getProximoId() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Rodada getPorId(Long id) {
    for (Rodada rodada: pool) {
      if (rodada.getId().equals(id)) {
        return rodada;
      }
    }

    throw new RuntimeException("Não foi possível encontrar rodada com o id: " + id);
  }

  @Override
  public List<Rodada> getPorJogador(Jogador jogador) {
    List<Rodada> rodadas = new ArrayList<>();

    for (Rodada rodada: pool) {
      if (rodada.getJogador() == jogador) {
        rodadas.add(rodada);
      }
    }

    return rodadas;
  }

  @Override
  public void inserir(Rodada rodada) throws RepositoryException {
    if (pool.contains(rodada)) {
      throw new RepositoryException("Essa rodada já está salvo no repositório");
    }      
    
    pool.add(rodada);
  }

  @Override
  public void atualizar(Rodada rodada) throws RepositoryException {
    this.remover(rodada);
    this.inserir(rodada);
  }

  @Override
  public void remover(Rodada rodada) throws RepositoryException {
    if (pool.contains(rodada)) {
        pool.remove(rodada);
    } else {
        throw new RepositoryException("A rodada " + rodada + "não está presente no repositório");
    }
  }

  
}