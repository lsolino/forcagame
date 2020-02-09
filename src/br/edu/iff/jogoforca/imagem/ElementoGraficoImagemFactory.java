package br.edu.iff.jogoforca.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory {
  
  private static ElementoGraficoImagemFactory soleInstance = null;

  public static ElementoGraficoImagemFactory getSoleInstance() {
    if (soleInstance == null) {
      soleInstance = new ElementoGraficoImagemFactory();
    }

    return soleInstance;
  }

  private BonecoImagemFactory bonecoFactory;
  private LetraImagemFactory letraFactory;

  private ElementoGraficoImagemFactory() {
      bonecoFactory = BonecoImagemFactory.getSoleInstance();
      letraFactory = LetraImagemFactory.getSoleInstance();
  }

  @Override
  public Boneco getBoneco() {
      return bonecoFactory.getBoneco();
  }

  @Override
  public Letra getLetra(char codigo) {
      return letraFactory.getLetra(codigo);
  }

  @Override
  public Letra getLetraEncoberta() {
      return letraFactory.getLetraEncoberta();
  }
}