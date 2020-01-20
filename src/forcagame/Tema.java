package forcagame;

public class Tema extends ObjetoDominioImpl {
    
    private String nome;
    
    private Tema(int id, String nome) {
        super(id);
        this.nome = nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public static Tema criar(int id, String nome) {
        return new Tema(id, nome);
    }
    
    public static Tema reconstituir(int id, String nome) {
        return new Tema(id, nome);
    }
    
}
