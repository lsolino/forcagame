package forcagame;

public class Tema extends ObjetoDominioImpl {
    
    private String nome;
    
    private Tema(Long id, String nome) {
        super(id);
        this.nome = nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public Tema criar(Long id, String nome) {
        return null;
        //TODO
    }
    
    public Tema reconstituir(Long id, String nome) {
        return null;
        //TODO
    }
    
}
