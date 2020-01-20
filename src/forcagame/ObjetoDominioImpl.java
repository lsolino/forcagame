package forcagame;

public abstract class ObjetoDominioImpl {
    
    private Long id;
    
    public ObjetoDominioImpl(Long id){
        this.id = id;
    }
    
    public Long getId(){
        return id;
    }
    
}
