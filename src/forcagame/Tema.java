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
    
    public Tema criar(int id, String nome) {
        return null;
        //TODO
    }
    
    public Tema reconstituir(int id, String nome) {
        return null;
        //TODO
    }
    
}
