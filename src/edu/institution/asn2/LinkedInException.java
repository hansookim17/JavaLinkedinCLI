package edu.institution.asn2;

public class LinkedInException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//No one exists in your connections
	public LinkedInException() {
		
	}
	
	//This user already exists in your connections
	public LinkedInException(String inString) {
		super(inString);
	}
	
	public LinkedInException(String inString, Throwable cause) {
		
	}
	
	protected LinkedInException(String inString, Throwable cause, Boolean enableSuppression, Boolean writableStackTrace) {
		
	}
	
	public LinkedInException(Throwable cause) {
		
	}
}
