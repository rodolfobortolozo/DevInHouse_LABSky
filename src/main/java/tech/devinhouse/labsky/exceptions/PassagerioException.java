package tech.devinhouse.labsky.exceptions;

public class PassagerioException extends RuntimeException{

  public PassagerioException(String msg){
    super(msg);
  }
  public PassagerioException(String msg, Throwable cause){
    super(msg, cause);
  }
}

