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


    @Test
    public void salvarCastateLivro(){
        Livro livro = new Livro();
        livro.setIsbn("12543554");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro de Ficcao");
        livro.setDataPublicacao(LocalDate.of(1980,1,1));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2000,1,1));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void salvarLivroEAutor(){
        Livro livro = new Livro();
        livro.setIsbn("12543554");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro de Ficcao");
        livro.setDataPublicacao(LocalDate.of(1980,1,1));

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2000,1,1));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutordoLivro(){
        UUID id = UUID.fromString("34b2670d-b555-4306-b8f2-83db2aa7f6ab");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("7d07b3b4-6256-4028-a556-d30b639931fc");
        var autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        livroRepository.save(livroParaAtualizar);

    }

    @Test
    public void deletarLivro(){
        UUID id = UUID.fromString("34b2670d-b555-4306-b8f2-83db2aa7f6ab");
        livroRepository.deleteById(id);
    }

    @Test
    public void buscarLivro(){
        UUID id = UUID.fromString("34b2670d-b555-4306-b8f2-83db2aa7f6ab");
        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Título");
        System.out.println(livro.getTitulo());
        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());
    }


}