package exceptions;
public class DriverAlreadyExist extends Exception {
 private static final long serialVersionUID = 1L;
 
 public DriverAlreadyExist()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public DriverAlreadyExist(String s)
  {
    super(s);
  }
}