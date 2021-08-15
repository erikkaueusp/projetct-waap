package br.com.waapgym.demo.controller.dto;

import br.com.waapgym.demo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {

    private Long id;
    private String nome;
    private  String email;
    private Byte[] imagem;


    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();


    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Byte[] getImagem() {
        return imagem;
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
