package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.jogoforca.JogadorNaoEncontradoException;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class RodadaAppService {
  
    private static RodadaAppService soleInstance;

    private RodadaRepository rodadaRepository;
    private RodadaFactory rodadaFactory;
    private JogadorRepository jogadorRepository;

    private RodadaAppService(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
        this.rodadaRepository = rodadaRepository;
        this.rodadaFactory = rodadaFactory;
        this.jogadorRepository = jogadorRepository;
    }

    public void createSoleInstance(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {

    }

    public static RodadaAppService getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new RodadaAppService(null, null, null);
        }
        return soleInstance;
    }

    public Rodada novaRodadaPorId(String idJogador) throws JogadorNaoEncontradoException {

        Long id = Long.parseLong(idJogador);

        try {
            Jogador retorno = jogadorRepository.getPorId(id);
            return rodadaFactory.getRodada(retorno);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        throw new JogadorNaoEncontradoException("Não foi possível encontrar o jogador" );
    }

    public Rodada novaRodadaPorNome(String nomeJogador) throws JogadorNaoEncontradoException{

        try {
            Jogador retorno = jogadorRepository.getPorNome(nomeJogador);
            return rodadaFactory.getRodada(retorno);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        throw new JogadorNaoEncontradoException("Não foi possível encontrar o jogador" );
    }

    public Rodada novaRodada(Jogador jogador) {
        try {
            jogadorRepository.inserir(jogador);	
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return null;
    }

    public void salvarRodada(Rodada rodada) throws RepositoryException{
        try {
            rodadaRepository.inserir(rodada);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}