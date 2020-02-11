package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.jogoforca.Aplicacao;
import br.edu.iff.repository.RepositoryException;

public class PalavraAppService {
	
    private static PalavraAppService soleInstance;

    private PalavraRepository repository;
    private PalavraFactory factory;

    private PalavraAppService(PalavraRepository repository, PalavraFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public static void createSoleInstance(PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        soleInstance = new PalavraAppService(palavraRepository, palavraFactory);
    }

    public static PalavraAppService getSoleInstance() {
        if(soleInstance == null) {
            soleInstance = new PalavraAppService(null, null);
          }

        return soleInstance;
    }

    public boolean novaPalavra(String palavra) throws RepositoryException{
        Palavra auxPalavra = repository.getPalavra(palavra);
        Aplicacao aplicacao = Aplicacao.getSoleInstance();
        if (auxPalavra == null) {
            TemaFactory temaFactory = aplicacao.getTemaFactory();
            Tema tema = temaFactory.getTema("Geral");
            Palavra novaPalavra = factory.getPalavra(palavra, tema);
            try {
                repository.inserir(novaPalavra);
                return true;
            } catch (RepositoryException re) {
                System.out.print(re.getMessage());
                return false;
            }
        }
        return true;
    }
}