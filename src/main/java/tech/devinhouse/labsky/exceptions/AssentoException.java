package tech.devinhouse.labsky.exceptions;

public class AssentoException extends RuntimeException{

  public AssentoException(String msg){
    super(msg);
  }
  public AssentoException(String msg, Throwable cause){
    super(msg, cause);
  }
}

