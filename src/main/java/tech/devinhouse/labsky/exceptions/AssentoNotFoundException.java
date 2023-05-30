package tech.devinhouse.labsky.exceptions;

public class AssentoNotFoundException extends RuntimeException{

  public AssentoNotFoundException(String msg){
    super(msg);
  }
  public AssentoNotFoundException(String msg, Throwable cause){
    super(msg, cause);
  }
}

