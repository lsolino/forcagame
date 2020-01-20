package forcagame;

public class LetraTextoFactory implements LetraFactory {

    @Override
    public Letra getLetra(char codigo) {
        return new LetraTexto(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return new LetraTexto('#');
    }
    
}
