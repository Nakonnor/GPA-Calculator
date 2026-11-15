public class InvalidCredit extends Exception {
	
	public InvalidCredit()
	{
		super("Grade must be from 0-100");
	}
	public InvalidCredit(String message)
	{
		super(message);
	}

}