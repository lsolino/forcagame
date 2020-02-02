package forcagame;

public abstract class LetraFactoryImpl implements LetraFactory {
	private Letra[] pool;
	private Letra encoberta;
	
	protected LetraFactoryImpl() {
		this.pool = new Letra[26];
		this.encoberta = null;
	}
	
	public final Letra getLetra(char codigo) {
		int i = codigo-'a';
		Letra resultado = this.pool[i];
		if(resultado == null) {
			resultado = this.criarLetra(codigo);
			this.pool[i] = resultado;
		}
		return resultado;
	}
	
	protected Letra criarLetra(char codigo) {
		return null;
	}
	
	public final Letra getLetraEncoberta() {
		if (encoberta == null) {
			char codigo = '#';
			this.encoberta = this.criarLetra(codigo);
		}
		return this.encoberta;
	}
}
