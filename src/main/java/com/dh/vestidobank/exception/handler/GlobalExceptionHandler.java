package com.dh.vestidobank.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dh.vestidobank.exception.DataIntegrityException;
import com.dh.vestidobank.exception.ObjectNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ResponseError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
		
		
		ResponseError responseError = ResponseError.builder()
				.timestamp(System.currentTimeMillis())
				.name("Objeto não encontrado")
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.status(HttpStatus.NOT_FOUND.value())
				.build();
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ResponseError> dataIntegrity(DataIntegrityException ex, HttpServletRequest request){
		
		ResponseError responseError = ResponseError.builder()
				.timestamp(System.currentTimeMillis())
				.name("Integridade dos dados")
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.build();
		
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseError);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationResponseError> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
		
		List<FieldError> errors = ex.getBindingResult().getFieldErrors().stream()
			.map(fe -> FieldError.builder()
					.field(fe.getField())
					.errorMessage(fe.getDefaultMessage())
					.build())
			.collect(Collectors.toList());
		
		ValidationResponseError err = ValidationResponseError.builder()
				.timestamp(System.currentTimeMillis())
				.name("Dados invalidos")
				.message("Erro de validação em: " + ex.getBindingResult().getObjectName())
				.path(request.getRequestURI())
				.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.errors(errors)
				.build();
		
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
