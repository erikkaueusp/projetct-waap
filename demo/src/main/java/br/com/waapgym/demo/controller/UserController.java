package br.com.waapgym.demo.controller;

import br.com.waapgym.demo.Usuario;
import br.com.waapgym.demo.controller.dto.UsuarioDto;
import br.com.waapgym.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsuarioRepository userRepository;

    @GetMapping
    public List<UsuarioDto> usuarios(String nome) {
        if (nome == null) {
            List<Usuario> usuarios = userRepository.findAll();
            return UsuarioDto.converter(usuarios);
        }
        else {
            List<Usuario> usuarios = userRepository.findBynomeIgnoreCase(nome);
            return UsuarioDto.converter(usuarios);
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioDto usuarioDto, UriComponentsBuilder uriBuilder) {
        Usuario usuario = new Usuario(usuarioDto.getNome(), usuarioDto.getEmail());
        userRepository.save(usuario);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }


}
