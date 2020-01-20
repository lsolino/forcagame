package forcagame;

import java.util.ArrayList;
import java.util.List;

public class Item extends ObjetoDominioImpl {
    
    private List<Boolean> posicoesDescobertas;
    private String palavraArriscada = null;
    private Palavra palavra;
    
    private Item(int id, Palavra palavra) {
        super(id);
        this.palavra = palavra;
    }
    
    private Item(int id, Palavra palavra, List<Integer> posicoesDescobertas, String palavraArriscada) {
        super(id);
        this.palavra = palavra;
        initializePosicoesDescobertas(palavra, posicoesDescobertas);
        this.palavraArriscada = palavraArriscada;
    }
    
    public Palavra getPalavra(){
        return this.palavra;
    }
    
    public List<Letra> getLetrasDescobertas(){
        
        List<Letra> uncover = new ArrayList<>();
        int i;

        for (i = 0; i < posicoesDescobertas.size(); i++) {
            if (posicoesDescobertas.get(i)) {
                uncover.add(palavra.getLetra(i));
            }
        }

        return uncover;
    }
    
    public List<Letra> getLetrasEncobertas(){
        List<Letra> cover = new ArrayList<>();
        int i;

        for (i = 0; i < posicoesDescobertas.size(); i++) {
            if (!posicoesDescobertas.get(i)) {
                cover.add(palavra.getLetra(i));
            }
        }

        return cover;
    }
    
    public int qtdeLetrasEncobertas(){
        return getLetrasEncobertas().size();
    }
    
    public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta) {
        int points = qtdeLetrasEncobertas() * valorPorLetraEncoberta;
        return points;
    }
    
    public boolean descobriu() {
        if (acertou() || qtdeLetrasEncobertas() == 0) {
            return true;
        } else {
            return false;
        } 
    }
    
    public void exibir(Object contexto) {
        palavra.exibir(contexto, posicoesDescobertas);
    }
    
    public boolean tentar(char codigo) {
        List<Integer> positions = new ArrayList<>();
        positions = palavra.tentar(codigo);

        if (positions.size() == 0)
            return false;
        
        for (Integer position: positions) {
            posicoesDescobertas.set(position, true);
        }

        return true;
    }
    
    public void arriscar(String palavra) {
        this.palavraArriscada = palavra;
    }
    
    public String getPalavraArriscada() {
        return this.palavraArriscada;
    }
    
    public boolean arriscou() {
        if (palavraArriscada != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean acertou() {
        if ( arriscou() ) {
            return palavra.comparar(palavraArriscada);
        } else {
            return false;
        }
    }
    
    //Checar modificador de acesso
    public static Item criar(int id, Palavra palavra) {
        return new Item(id, palavra);
    }
    
    public static Item reconstituir(int id, Palavra palavra, List<Integer> posicoesDescobertas, String palavraArriscada) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

    private void initializePosicoesDescobertas(Palavra palavra, List<Integer> posicoesDescobertas) {
        this.posicoesDescobertas = new ArrayList<>(palavra.getTamanho());
        int i = 0;

        for (i = 0; i < palavra.getTamanho(); i++) {
            this.posicoesDescobertas.add(false);
        }

        if (posicoesDescobertas != null) {
            for (Integer aux: posicoesDescobertas) {
                this.posicoesDescobertas.set(aux, true);
            }
        }
    }
    
}
    
  
