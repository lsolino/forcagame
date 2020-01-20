package forcagame;

//aplicar Singleton
public class BonecoTexto implements Boneco{
    
    private static BonecoTexto soleInstance = null;
    
    private static String[] partName = { "cabeça", "olho esquerdo", "olho direito", "nariz", "boca", "tronco", "braço esquerdo",
            "braço direito", "perna esquerda", "perna direita" };
    
    public static BonecoTexto getSoleInstance(){
        if (soleInstance == null) {
            soleInstance = new BonecoTexto();
        }

        return soleInstance;
    }
    
    private BonecoTexto(){
        
    }
    
    public void exibir(Object contexto, int partes) {
        int i;
        for (i=0; i < partes; i++){
            System.out.println(partName[i]);
        }
    }
    
}
