package tech.devinhouse.labsky.models;

public enum Classificacao {
  VIP(100),
  OURO(80),
  PRATA(50),
  BRONZE(30),
  ASSOCIADO(10);

  private int pontos;

  Classificacao(int pontos){
    this.pontos = pontos;
  }

  public int getPontos(){
    return pontos;
  }
}
