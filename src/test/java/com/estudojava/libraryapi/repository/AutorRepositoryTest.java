package com.estudojava.libraryapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.estudojava.libraryapi.model.Autor;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarAutor() {
        Autor autor = new Autor();
        autor.setNome("José");
        autor.setDataNascimento(LocalDate.of(1930,1,31));
        autor.setNacionalidade("Brasileira");

        autorRepository.save(autor);
    }

    @Test
    public void atualizarAutor(){

        var id = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

        Optional<Autor> autor = autorRepository.findById(id);

        if(autor.isPresent()){
            Autor autorAtualizado = autor.get();
            System.out.println("Dados do Autor");
            System.out.println(autorAtualizado);

            autorAtualizado.setDataNascimento(LocalDate.of(1990,1,31));

            autorRepository.save(autorAtualizado);
        }

    }

    @Test
    public void listarTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void contTest(){
        System.out.println("contagem de autores" + autorRepository.count());
    }

    @Test
    public void deletarPorIdTest(){
        var id = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");

        autorRepository.deleteById(id);
    }

    @Test
    public void deletarPorObjetoTest(){

        var id = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
        var autor = autorRepository.findById(id).get();

        autorRepository.delete(autor);
    }




}
