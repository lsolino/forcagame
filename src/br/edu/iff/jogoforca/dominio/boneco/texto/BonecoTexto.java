package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco {

  private static BonecoTexto soleInstance = null;

  private static String[] partName = { 
    "Cabeça", 
    "Olho Esquerdo", 
    "Olho Direito", 
    "Nariz", 
    "Boca", 
    "Tronco", 
    "Braço Esquerdo",
    "Braço Direito", 
    "Perna Esquerda", 
    "Perna Direita" 
  };

  public static BonecoTexto getSoleInstance() {
    if (soleInstance == null) {
      soleInstance = new BonecoTexto();
    }

    return soleInstance;
  }

  private BonecoTexto() {}

  @Override
  public void exibir(Object contexto, int partes) {
    for (int i = 0; i < partes; i++) {
      System.out.print(partName[i]);

      if ((i + 1) != partes) {
        System.out.println("");
      }
        
    }
  }
}