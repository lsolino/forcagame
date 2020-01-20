package forcagame;

public class BonecoTextoFactory implements BonecoFactory {

    @Override
    public Boneco getBoneco() {
        return BonecoTexto.getSoleInstance();
    }
    
}