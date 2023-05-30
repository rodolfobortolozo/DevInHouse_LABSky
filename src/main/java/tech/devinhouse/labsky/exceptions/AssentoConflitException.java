package tech.devinhouse.labsky.exceptions;

public class AssentoConflitException extends RuntimeException{

  public AssentoConflitException(String msg){
    super(msg);
  }
  public AssentoConflitException(String msg, Throwable cause){
    super(msg, cause);
  }
}

