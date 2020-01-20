package forcagame;

public class Rodada extends ObjetoDominioImpl {
    
    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosQuandoDescobreTodasAsPalavras = 100;
    private static int pontosPorLetraEncoberta = 15;
    private Palavra[] palavras;
    private Item[] itens;
    private Jogador jogador;
    private Letra[] letrasErradas;
    private Boneco boneco;
    private BonecoFactory bonecoFactory;
    
    private Rodada(Long id, Palavra[] palavras, Jogador jogador) {
        super(id);
        this.palavras = palavras;
        this.jogador = jogador;
    }
    
    private Rodada(Long id, Item[] itens, Letra[] erradas, Jogador jogador) {
        super(id);
        this.itens = itens;
        this.letrasErradas = erradas;
        this.jogador = jogador;
    }
    
    public Jogador getJogador(){
        return null;
        //TODO
    }
    
    public Tema getTema(){
        return null;
        //TODO
    }
    
    public Palavra[] getPalavras() {
       return null;
       //TODO
    }
    
    public int getNumPalavras(){
        return 0;
        //TODO
    }
    
    public void tentar(char codigo) {
        //TODO
    }
    
    public void arriscar(String[] palavras) {
        //TODO
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
    
    public Letra[] getTentativas(){
        return null;
        //TODO
    }
    
    public Letra[] getCertas(){
        return null;
        //TODO
    }
    
    public Letra[] getErradas(){
        return null;
        //TODO
    }
    
    public int calcularPontos(){
        return 0;
        //TODO
    }
    
    public boolean encerrou(){
        return false;
        //TODO
    }
    
    public boolean descobriu(){
        return false;
        //TODO
    }
    
    public boolean arriscou(){
        return false;
        //TODO
    }
    
    public int getQtdeTentativaRestantes(){
        return 0;
        //TODO
    }
    
    public int getQtdeErros(){
        return 0;
        //TODO
    }
    
    public int getQtdeAcertos(){
        return 0;
        //TODO
    }
    
    public int getQtdeTentativas(){
        return 0;
        //TODO
    }
    
    public static int getMaxPalavras(){
        return maxPalavras;
    }
    
    public static void setMaxPalavras(int max) {
        //TODO Se o atributo é estático como irei setar?
    }
    
    public static int getMaxErros() {
        return maxErros;
    }
    
    public static void setMaxErros(int max) {
        //TODO Se o atributo é estático como irei setar?
    }
    
    public static int getPontosQuandoDescobreTodasAsPalavras() {
        return pontosQuandoDescobreTodasAsPalavras;
    }
    
    public static void setPontosQuandoDescobreTodasAsPalavras(int pontos) {
        //TODO Se o atributo é estático como irei setar?
    }
    
    public static int getPontosPorLetraEncoberta() {
        return pontosPorLetraEncoberta;
    }
    
    public static void setPontosPorLetraEncoberta(int pontos) {
        //TODO Se o atributo é estático como irei setar?
    }
    
    public void setBonecoFactory(BonecoFactory bonecoFactory){
        //TODO
    }
    
    public BonecoFactory getBonecoFactory(){
        return null;
        //TODO
    }
    
    public Rodada criar(Long id, Palavra[] palavras, Jogador jogador) {
        return null;
        //TODO
    }
    
    public Rodada reconstituir(Long id, Item[] itens, Letra[] erradas, Jogador jogador) {
        return null;
        //TODO
    }
    
}
