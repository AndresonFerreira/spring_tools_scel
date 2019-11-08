package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.fatec.scel.model.Livro;
import com.fatec.scel.model.Servico;
import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;

class REQ05CadastrarUsuario {

	@Autowired
	UsuarioRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;

	/*
	@Test
	public void CT01CadastrarLivroComSucesso() {
		repository.deleteAll();

		Usuario usuario = new Usuario(null,"1233-33","Andreson", "andreson@email.com", "03987050", "Rua Joaquim Mariano"); 

		repository.save(usuario);

		assertEquals(1, repository.count());
	}*/
	

	@Test 
	public void CT01CadastrarUsuarioComSucesso_dados_validos() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Servico servico = new Servico();
		Usuario usuario = new Usuario(null, "1233-33","Andreson", "andreson@email.com", "03987050", servico.obtemEndereco("03987050")); 

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
	
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void CT02DeveDetectarNomeInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		
		Usuario usuario = new Usuario(null, "1233-33", null, "andreson@email.com", "03987050", "Rua Joaquim Mariano"); 

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		
		assertEquals(violations.size(), 1);
		assertEquals("O nome deve ser preenchido", violations.iterator().next().getMessage());
	}
	//O nome deve ser preenchido

	@Test
	public void CT03DeveDetectarRaInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		
		Usuario usuario = new Usuario(null, null,"Andreson", "andreson@email.com", "03987050", "Rua Joaquim Mariano"); 

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		
		assertEquals(violations.size(), 1);
		assertEquals("O ra deve ser preenchido", violations.iterator().next().getMessage());
	}
	//O ra deve ser preenchido
	
	
	
	
	@Test
	public void CT02DeveDetectarEmailInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		
		Usuario usuario = new Usuario(null, "1233-33","Andreson", null, "03987050", "Rua Joaquim Mariano"); 

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		
		assertEquals(violations.size(), 1);
		assertEquals("O e-mail deve ser preenchido", violations.iterator().next().getMessage());
	}
	//O e-mail deve ser preenchido
	
	
	//GET
	@Test
	public void CT01ConsultarUsuarioGET_valido(){
	
		Usuario usuario = new Usuario(null, "1233-33","Andreson", "andreson@email.com", "03987050", "Rua Joaquim Mariano"); 
		
		assertEquals("Andreson",usuario.getNome());
		assertEquals("1233-33", usuario.getRa());
		assertEquals("andreson@email.com", usuario.getEmail());
		assertEquals("03987050", usuario.getCep());
		assertEquals("Rua Joaquim Mariano", usuario.getEndereco());
		assertEquals(null, usuario.getId());

	}
	
	
}
