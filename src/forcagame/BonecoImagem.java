package forcagame;

//aplicar Singleton
public class BonecoImagem implements Boneco{
    
    private static BonecoImagem soleInstance = null;
    
    public BonecoImagem getSoleInstance(){
        if (soleInstance == null) {
            soleInstance = new BonecoImagem();
        }

        return soleInstance;
    }
    
    private BonecoImagem(){
        
    }
    
    public void exibir(Object contexto, int partes) {
        
    }
    
}
