package com.dh.vestidobank.exception;

public class ClienteFalidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClienteFalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClienteFalidoException(String message) {
		super(message);
	}
	
	

}
