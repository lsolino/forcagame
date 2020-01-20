package forcagame;

public class Jogador extends ObjetoDominioImpl {
    
    private String nome;
    private int pontuacao = 0;
    
    private Jogador(Long id, String nome) {
        super(id);
        this.nome = nome;
    }
    
    private Jogador(Long id, String nome, int pontuacao) {
        super(id);
        this.nome = nome;
        this.pontuacao = pontuacao;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getPontuacao(){
        return pontuacao;
    }
    
    //Checar o modificador de acesso
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public static Jogador criar(Long id, String nome) {
       return new Jogador(id, nome);
    }
    
    public static Jogador reconstituir(Long id, String nome, int pontuacao) {
        return new Jogador(id, nome, pontuacao);
    }
    
}
