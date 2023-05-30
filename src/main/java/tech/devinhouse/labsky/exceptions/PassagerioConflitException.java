package tech.devinhouse.labsky.exceptions;

public class PassagerioConflitException extends RuntimeException{

  public PassagerioConflitException(String msg){
    super(msg);
  }
  public PassagerioConflitException(String msg, Throwable cause){
    super(msg, cause);
  }
}

