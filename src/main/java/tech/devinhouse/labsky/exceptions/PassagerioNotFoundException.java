package tech.devinhouse.labsky.exceptions;

public class PassagerioNotFoundException extends RuntimeException{

  public PassagerioNotFoundException(String msg){
    super(msg);
  }
  public PassagerioNotFoundException(String msg, Throwable cause){
    super(msg, cause);
  }
}

