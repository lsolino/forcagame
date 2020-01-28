package forcagame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Palavra extends ObjetoDominioImpl {

    private List<Letra> palavras;
    private Tema tema;
    private static LetraFactory letraFactory;
    
    private Palavra(int id, String palavra, Tema tema) {
        super(id);
        int i;
        LetraFactory factory = getLetraFactory();
        
        if (factory == null) {
            throw new RuntimeException("A fábrica de Letra ainda não foi inicializada.");
        }
        
        this.palavras = new ArrayList<>();
        
        for (i = 0; i < palavra.length(); i++) {
            this.palavras.add(factory.getLetra(palavra.charAt(i)));
        }
        
        this.tema = tema;
      
    }
    
    public List<Letra> getLetras() {
        return Collections.unmodifiableList(this.palavras);
    }
    
    public Letra getLetra(int posicao) {
        return this.palavras.get(posicao);
    }
    
    public void exibir(Object contexto) {
        for (Letra letra: palavras) {
            letra.exibir(null);
        }
    }
    
    public void exibir(Object contexto, List<Boolean> posicoes) {
        if (posicoes.size() != palavras.size()) {
            throw new RuntimeException("Posicoes não tem o mesmo tamanho da palavra!");
        }

        LetraFactory factory = getLetraFactory();
        Letra letraEncoberta = factory.getLetraEncoberta();

        for (int i = 0; i < palavras.size(); i++) {
            if (posicoes.get(i)) {
                palavras.get(i).exibir(null);
            } else {
                letraEncoberta.exibir(null);
            }
        }
    }
    
    public List<Integer> tentar(char codigo) {
        List<Integer> posicoesEncontradas = new ArrayList<>();

        for (int i = 0; i < palavras.size(); i++) {
            if (palavras.get(i).getCodigo() == codigo)
                posicoesEncontradas.add(i);
        }
        return posicoesEncontradas;
    }
    
    public Tema getTema() {
        return this.tema;
    }
    
    public boolean comparar(String palavra) {
        int i;
        for (i = 0; i < this.palavras.size(); i++) {
            if (this.palavras.get(i).getCodigo() != palavra.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public int getTamanho(){
        return this.palavras.size();
    }
    
    public static void setLetraFactory(LetraFactory factory){
        letraFactory = factory;
    }
    
    public static LetraFactory getLetraFactory(){
        return letraFactory;
    }
    
    public static Palavra criar(int id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }
    
    public static Palavra reconstituir(int id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }
    
    @Override
    public String toString() {
        return "Palavra [palavra=" + palavras + ", tema=" + tema + "]";
    }
    
}
