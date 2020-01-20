/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forcagame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dsn2
 */
public class ForcaGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Palavra.setLetraFactory(new LetraTextoFactory());
        Rodada.setBonecoFactory(new BonecoTextoFactory());

        Jogador jogador = Jogador.criar(1, "Solino");

        Tema tema = Tema.criar(1, "Futebol");

        List<Palavra> palavras = new ArrayList<>();
        palavras.add(Palavra.criar(1, "fla", tema));
        palavras.add(Palavra.criar(2, "flu", tema));
        palavras.add(Palavra.criar(3, "lau", tema));

        Rodada rodada = Rodada.criar(1, palavras, jogador);
        rodada.exibirItens(null);
        System.out.println();
        rodada.tentar('a');
        rodada.tentar('e');
        rodada.tentar('g');
        rodada.tentar('f');
        rodada.tentar('l');

        rodada.exibirItens(null);
        System.out.println();

        if (rodada.descobriu()) {
            System.out.print("Descobriu");
        } else {
            System.out.print("Não descobriu");
        }
    }
    
}
