package br.edu.iff.bancodepalavras.dominio.palavra;

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
	
	public void novaPalavra(Palavra palavra) throws RepositoryException{
		try {
			repository.inserir(palavra);	
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}