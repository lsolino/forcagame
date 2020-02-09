package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaJogadorRepository implements JogadorRepository {

  private static MemoriaJogadorRepository soleInstance = null;

  public static MemoriaJogadorRepository getSoleInstance() {
    if (soleInstance == null) {
      soleInstance = new MemoriaJogadorRepository();
    }
      
    return soleInstance;
  }

  private List<Jogador> pool;

  private MemoriaJogadorRepository() {
    pool = new ArrayList<>();
  }

  @Override
  public long getProximoId() {
    return pool.size() + 1;
  }

  @Override
  public Jogador getPorId(Long id) {
    for (Jogador jogador: pool) {
      if (jogador.getId().equals(id)) {
        return jogador;
      }
    }

    throw new RuntimeException("Não foi possível encontra nenhuma jogador com o ID: " + id);
  }

  @Override
  public Jogador getPorNome(String nome) {
    for (Jogador jogador: pool) {
      if (jogador.getNome().equals(nome)) {
        return jogador;
      }      
    }

    throw new RuntimeException("Não foi possível encontrar o jogador: " + nome);
  }

  @Override
  public void inserir(Jogador jogador) throws RepositoryException {
    if (pool.contains(jogador)) {
      throw new RepositoryException("Jogador já está salvo no repositório");
    }
    pool.add(jogador);
  }

  @Override
  public void atualizar(Jogador jogador) throws RepositoryException {
    // TODO Auto-generated method stub

  }

  @Override
  public void remover(Jogador jogador) throws RepositoryException {
    // TODO Auto-generated method stub

  }

  
}