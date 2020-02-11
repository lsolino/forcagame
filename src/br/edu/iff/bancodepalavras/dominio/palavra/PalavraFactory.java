package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import java.util.Scanner;

public interface PalavraFactory {

  public Palavra getPalavra(String palavra, Tema tema);
  
}