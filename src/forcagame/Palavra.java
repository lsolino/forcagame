package forcagame;

import java.util.List;

public class Palavra extends ObjetoDominioImpl {

    private String palavra;
    private Tema tema;
    
    private Palavra(int id, String palavra, Tema tema) {
        super(id);
        this.palavra = palavra;
        this.tema = tema;
    }
    
    public Letra[] getLetras() {
        return null;
        //TODO
    }
    
    public Letra getLetra(int posicao) {
        return null;
        //TODO
    }
    
    public void exibir(Object contexto) {
        //TODO
    }
    
    public void exibir(Object contexto, List<Boolean> posicoes) {
        //TODO
    }
    
    public List<Integer> tentar(char codigo) {
        return null;
        //TODO
    }
    
    public Tema getTema() {
        return null;
        //TODO
    }
    
    public boolean comparar(String palavra) {
        return false;
        //TODO
    }
    
    public int getTamanho(){
        return 0;
        //TODO
    }
    
    public void setLetraFactory(LetraFactory factory){
        //TODO
    }
    
    public LetraFactory getLetraFactory(){
        return null;
        //TODO
    }
    
    public Palavra criar(Long id, String palavra, Tema tema) {
        return null;
        //TODO
    }
    
    public Palavra reconstituir(Long id, String palavra, Tema tema) {
        return null;
        //TODO
    }
    
    @Override
    public String toString(){
        return null;
        
    }
    
}
