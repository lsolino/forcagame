package br.edu.iff.jogoforca;


public class JogadorNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String jogador;

	public JogadorNaoEncontradoException(String jogador) {
    super(jogador);
	}
	
	public String getJogador() {
		return null;
	}
}