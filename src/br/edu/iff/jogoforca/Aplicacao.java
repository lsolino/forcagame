package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import br.edu.iff.jogoforca.embdr.BDRRepositoryFactory;
import br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory;
import br.edu.iff.jogoforca.imagem.ElementoGraficoImagemFactory;
import br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory;

public class Aplicacao {
	private final static String[] TIPOS_REPOSITORY_FACTORY = { "memoria", "relacional" };
	private final static String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = { "texto", "imagem" };
	private final static String[] TIPOS_RODADA_FACTORY = { "sorteio" };

	private static Aplicacao soleInstance = null;

	public static Aplicacao getSoleInstance() {
			if (soleInstance == null)
					soleInstance = new Aplicacao();

			return soleInstance;
	}

	private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
	private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
	private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

	private RepositoryFactory repositoryFactory;
	private ElementoGraficoFactory elementoGraficoFactory;
	private RodadaFactory rodadaFactory;

	private Aplicacao() {
			setup();
	}

	public void setup() {
			if (tipoRepositoryFactory.equals(TIPOS_REPOSITORY_FACTORY[0])) {
					repositoryFactory = MemoriaRepositoryFactory.getSoleInstance();
			} else if (tipoRepositoryFactory.equals(TIPOS_REPOSITORY_FACTORY[1])) {
					repositoryFactory = BDRRepositoryFactory.getSoleInstance();
			} else {
					throw new RuntimeException("esse tipo de fabrica de repositorios não existe");
			}

			if (tipoElementoGraficoFactory.equals(TIPOS_ELEMENTO_GRAFICO_FACTORY[0])) {
					elementoGraficoFactory = ElementoGraficoTextoFactory.getSoleInstance();
			} else if (tipoElementoGraficoFactory.equals(TIPOS_ELEMENTO_GRAFICO_FACTORY[1])) {
					elementoGraficoFactory = ElementoGraficoImagemFactory.getSoleInstance();
			} else {
					throw new RuntimeException("esse tipo de fabrica de elementos gráficos não existe");
			}

			if (tipoRodadaFactory.equals(TIPOS_RODADA_FACTORY[0])) {
					RodadaSorteioFactory.createSoleInstance(repositoryFactory.getRodadaRepository(),
									repositoryFactory.getTemaRepository(), repositoryFactory.getPalavraRepository());
					rodadaFactory = RodadaSorteioFactory.getSoleInstance();
			} else {
					throw new RuntimeException("esse tipo de fabrica de rodadas não existe");
			}

			TemaFactoryImpl.createSoleInstance(repositoryFactory.getTemaRepository());
			PalavraFactoryImpl.createSoleInstance(repositoryFactory.getPalavraRepository());
			JogadorFactoryImpl.createSoleInstance(repositoryFactory.getJogadorRepository());
			Palavra.setLetraFactory(elementoGraficoFactory);
			Rodada.setBonecoFactory(elementoGraficoFactory);
	}

	public String[] getTiposRepositoryFactory() {
			return TIPOS_REPOSITORY_FACTORY;
	}

	public void setTipoRepositoryFactory(String tipo) {
			tipoRepositoryFactory = tipo;
			setup();
	}

	public RepositoryFactory getRepositoryFactory() {
			return repositoryFactory;
	}

	public String[] getTiposElementoGraficoFactory() {
			return TIPOS_ELEMENTO_GRAFICO_FACTORY;
	}

	public void setTipoElementoGraficoFactory(String tipo) {
			tipoElementoGraficoFactory = tipo;
			setup();
	}

	public ElementoGraficoFactory getElementoGraficoFactory() {
			return elementoGraficoFactory;
	}

	public String[] getTiposRodadaFactory() {
			return TIPOS_RODADA_FACTORY;
	}

	public void setTipoRodadaFactory(String tipo) {
			tipoRodadaFactory = tipo;
			setup();
	}

	public RodadaFactory getRodadaFactory() {
			return rodadaFactory;
	}

	public TemaFactory getTemaFactory() {
			return TemaFactoryImpl.getSoleInstance();
	}

	public PalavraFactory getPalavraFactory() {
			return PalavraFactoryImpl.getSoleInstance();
	}

	public JogadorFactory getJogadorFactory() {
			return JogadorFactoryImpl.getSoleInstance();
	}
}