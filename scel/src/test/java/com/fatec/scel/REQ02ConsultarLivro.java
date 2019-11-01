package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ02ConsultarLivro {
	@Autowired
	LivroRepository repository;
	static Livro livro;

	
	@Test
	public void test() {
// dado que o livro esta cadastrado
		livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		// quando o usurio consulta
		Livro ro = repository.findByIsbn("3333");
// entao o sistema valida as 
		assertThat(ro.getIsbn()).isEqualTo(livro.getIsbn());
		assertThat(ro.getTitulo()).isEqualTo(livro.getTitulo());
		assertThat(ro.getAutor()) .isEqualTo(livro.getAutor());
		assertThat(ro.getId()) .isEqualTo(livro.getId());
	}
	
	

	
} 