package br.com.waapgym.demo.controller;

import br.com.waapgym.demo.Usuario;
import br.com.waapgym.demo.controller.dto.UsuarioDto;
import br.com.waapgym.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;


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

    @PostMapping("/upload")
    public void uploadImage(@RequestParam("image") MultipartFile file) {
        Usuario userTest = new Usuario();
        userTest.setNome("KAKAROTO");

        try {
            userTest.setImagem(file.getBytes());
            userRepository.save(userTest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("FOII");
        System.out.println(userTest.getImagem());
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public byte[] showImage(@PathVariable("id") Long id){

        Usuario user = userRepository.getOne(id);
        return user.getImagem();
    }


}
