package forcagame;

public class Item extends ObjetoDominioImpl {
    
    private boolean[] posicoesDescobertas;
    private String palavraArriscada = null;
    private Palavra palavra;
    
    private Item(Long id, Palavra palavra) {
        super(id);
        this.palavra = palavra;
    }
    
    private Item(Long id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada) {
        super(id);
        this.palavra = palavra;
        // int para boolean? this.posicoesDescobertas = posicoesDescobertas;
        this.palavraArriscada = palavraArriscada;
    }
    
    public Palavra getPalavra(){
        return null;
        //TODO
    }
    
    public Letra[] getLetrasDescobertas(){
        return null;
        //TODO
    }
    
    public Letra[] getLetrasEncobertas(){
        return null;
        //TODO
    }
    
    public int qtdeLetrasEncobertas(){
        return 0;
        //TODO
    }
    
    public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta) {
        return 0;
        //TODO
    }
    
    public boolean descobriu() {
        return false;
        //TODO
    }
    
    public void exibir(Object contexto) {
        //TODO
    }
    
    public boolean tentar(char codigo) {
        return false;
        //TODO
    }
    
    public void arriscar(String palavra) {
        //TODO
    }
    
    public String getPalavraArriscada() {
        return null;
        //TODO
    }
    
    public boolean arriscou() {
        return false;
        //TODO
    }
    
    public boolean acertou() {
        return false;
        //TODO
    }
    
    //Checar modificador de acesso
    public Item criar(Long id, Palavra palavra) {
        return null;
        //TODO
    }
    
    public Item reconstituir(Long id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada) {
        return null;
        //TODO
    }
    
}
    
  
