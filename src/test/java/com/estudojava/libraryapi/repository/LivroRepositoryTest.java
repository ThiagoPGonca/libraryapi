package com.estudojava.libraryapi.repository;

import com.estudojava.libraryapi.model.Autor;
import com.estudojava.libraryapi.model.GeneroLivro;
import com.estudojava.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarLivro(){
        Livro livro = new Livro();
        livro.setIsbn("12543554");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Livro de Ficcao");
        livro.setDataPublicacao(LocalDate.of(1980,1,1));

        Autor autor = autorRepository.findById(UUID.fromString("123e4567-e89b-12d3-a456-426655440000")).orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

}