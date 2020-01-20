package forcagame;

public abstract class Letra {
    
    private char codigo;
    
    protected Letra(char codigo) {
        this.codigo = codigo;
    }
    
    public char getCodigo() {
        return codigo;
    }
    
    public void exibir(Object contexto) {
        //TODO
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Letra))
            return false;

        Letra outra = (Letra) o;
        return this.codigo == outra.codigo && this.getClass().equals(outra.getClass());
    }
    
    public int hashcode() {
        return this.codigo + this.getClass().hashCode();
    }
    
    public final String toString() {
        return null;
        //TODO
    }
}
