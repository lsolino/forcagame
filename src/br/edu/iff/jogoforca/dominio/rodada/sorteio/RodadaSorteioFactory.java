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
import java.util.Collection;
import java.util.Collections;

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

    @Override
    public Rodada getRodada(Jogador jogador) {
        Random random = new Random();
        List<Tema> todosTemas = getTemaRepository().getTodos();
        Tema temaSorteado = todosTemas.get(random.nextInt(todosTemas.size()));
        List<Palavra> todasPalavras = getPalavraRepository().getPorTema(temaSorteado);
        int maxPalavras = Rodada.getMaxPalavras();
        int minPalavras = 1;
        int i = 0;
        int qtdePalavras = random.nextInt( (maxPalavras+1) - minPalavras ) + minPalavras;
        List<Palavra> palavrasSorteadas = new ArrayList();
        Palavra auxPalavra = null;
        
        while (palavrasSorteadas.size() != qtdePalavras) {
            auxPalavra = todasPalavras.get(random.nextInt(todasPalavras.size()));
            if (!palavrasSorteadas.contains(auxPalavra) && palavrasSorteadas.size() != 3) {
                palavrasSorteadas.add(auxPalavra);
            }
        }

        Rodada rodada = Rodada.criar(getProximoId(), palavrasSorteadas, jogador);
        palavrasSorteadas.clear();

        try{
          getRodadaRepository().inserir(rodada);
        } catch (RepositoryException repositoryException) {
          throw new RuntimeException("Erro ao salvar rodada no repositório.");
        }

        return rodada;
    }

  
}