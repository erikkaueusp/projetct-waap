package br.com.waapgym.demo.repository;

import br.com.waapgym.demo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    List<Usuario> findBynomeIgnoreCase(String nome);
}
