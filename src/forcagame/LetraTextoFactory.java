package forcagame;

public class LetraTextoFactory implements LetraFactory {

	private static LetraTextoFactory soleInstance = null;
	
	private LetraTextoFactory() {
	}
	
	public static LetraTextoFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new LetraTextoFactory();
		}
		return soleInstance;
	}
	
    @Override
    public Letra getLetra(char codigo) {
        return new LetraTexto(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return new LetraTexto('#');
    }
    
	protected Letra criarLetra(char codigo) {
		return new LetraTexto(codigo);
	}
    
}
