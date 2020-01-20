package forcagame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rodada extends ObjetoDominioImpl {
    
    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosQuandoDescobreTodasAsPalavras = 100;
    private static int pontosPorLetraEncoberta = 15;
    private List<Palavra> palavras;
    private List<Item> itens;
    private Jogador jogador;
    private List<Letra> letrasErradas;
    private List<Letra> letrasCertas;
    private Boneco boneco;
    private BonecoFactory bonecoFactory;
    
    private Rodada(int id, List<Palavra> palavras, Jogador jogador) {
        super(id);
        int i;
        if (getBonecoFactory() == null) {
            throw new RuntimeException("A fábrica do boneco ainda não foi inicializada");
        }
        boneco = getBonecoFactory().getBoneco();
        this.itens = new ArrayList<>(palavras.size());
        this.jogador = jogador;
        
        for (i=0; i< palavras.size(); i++) {
            this.itens.add(Item.criar(i, palavras.get(i)));
        }
    }
    
    private Rodada(int id, List<Item> itens, List<Letra> erradas, Jogador jogador) {
        super(id);
        int i;
        
        if (getBonecoFactory() == null) {
            throw new RuntimeException("A fábrica do boneco ainda não foi inicializada.");
        }
        
        boneco = getBonecoFactory().getBoneco();

        this.jogador = jogador;
        this.itens = new ArrayList<>(itens.size());

        for (i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            this.itens.add(item);

            for (Letra correta: item.getLetrasDescobertas()) {
                this.letrasCertas.add(correta);
            }
        }

        for (Letra errada: erradas) {
            this.letrasErradas.add(errada);
        }
    }
    
    public Jogador getJogador(){
       return this.jogador;
    }
    
    public Tema getTema(){
        return this.itens.get(0).getPalavra().getTema();
    }
    
    public List<Palavra> getPalavras() {
        
        List<Palavra> words = new ArrayList<>();
        
        for (Item i: itens) {
            words.add(i.getPalavra());
        }
        return words;
    }
    
    public int getNumPalavras(){
        return itens.size();
    }
    
    public void tentar(char codigo) {
        //TODO
    }
    
    public void arriscar(List<String> palavras) {
        if (encerrou()) {
            return;
        }

        int aux;
        int i;
        
        if (palavras.size() < itens.size()) {
            aux = palavras.size();
        } else {
            aux = itens.size();
        }

        for (i = 0; i < aux; i++) {
            itens.get(i).arriscar(palavras.get(i));
        }

    }
    
    public void exibirItens(Object contexto) {
        //TODO
    }
    
    public void exibirBoneco(Object contexto){
        //TODO
    }
    
    public void exibirPalavras(Object contexto) {
        //TODO
    }
    
    public List<Letra> getTentativas(){
        List<Letra> attempts = new ArrayList<>();
        attempts.addAll(letrasCertas);
        attempts.addAll(letrasErradas);

        return attempts;
    }
    
    public List<Letra> getCertas(){
       return Collections.unmodifiableList(letrasCertas);
    }
    
    public List<Letra> getErradas(){
        return Collections.unmodifiableList(letrasErradas);
    }
    
    public int calcularPontos(){
        if (!descobriu()) {
            return 0;
        }
        
        int points = getPontosQuandoDescobreTodasAsPalavras();

        for (Item item: itens) {
            points += item.calcularPontosLetrasEncobertas(getPontosPorLetraEncoberta());
        }

        return points;
    }
    
    public boolean encerrou(){
        if (arriscou() || descobriu() || getQtdeTentativaRestantes() == 0) {
            return true;
        }

        return false;
    }
    
    public boolean descobriu(){
        for (Item item: itens)
            if (!item.descobriu()) {
                return false;
            }
        
        return true;
    }
    
    public boolean arriscou(){
        for (Item item: itens)
            if (item.arriscou()) {
                return true;
            }
        
        return false;
    }
    
    public int getQtdeTentativaRestantes(){
        return getMaxErros() - letrasErradas.size();
    }
    
    public int getQtdeErros(){
        return letrasErradas.size();
    }
    
    public int getQtdeAcertos(){
        return letrasCertas.size();
    }
    
    public int getQtdeTentativas(){
        return getQtdeErros() + getQtdeAcertos();
    }
    
    public static int getMaxPalavras(){
        return maxPalavras;
    }
    
    public static void setMaxPalavras(int max) {
        maxPalavras = max;
    }
    
    public static int getMaxErros() {
        return maxErros;
    }
    
    public static void setMaxErros(int max) {
        maxErros = max;
    }
    
    public static int getPontosQuandoDescobreTodasAsPalavras() {
        return pontosQuandoDescobreTodasAsPalavras;
    }
    
    public static void setPontosQuandoDescobreTodasAsPalavras(int pontos) {
        pontosQuandoDescobreTodasAsPalavras = pontos;
    }
    
    public static int getPontosPorLetraEncoberta() {
        return pontosPorLetraEncoberta;
    }
    
    public static void setPontosPorLetraEncoberta(int pontos) {
        pontosPorLetraEncoberta = pontos;
    }
    
    public void setBonecoFactory(BonecoFactory bonecoFactory){
        bonecoFactory = bonecoFactory;
    }
    
    public BonecoFactory getBonecoFactory(){
        return bonecoFactory;
    }
    
    public Rodada criar(int id, List<Palavra> palavras, Jogador jogador) {
        return new Rodada(id, palavras, jogador);
    }
    
    public Rodada reconstituir(int id, List<Item> itens, List<Letra> erradas, Jogador jogador) {
        return new Rodada(id, itens, erradas, jogador);
    }
    
}
