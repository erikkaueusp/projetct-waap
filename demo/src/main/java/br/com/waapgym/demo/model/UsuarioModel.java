package br.com.waapgym.demo.model;

import br.com.waapgym.demo.Usuario;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class UsuarioModel {


    private String nome;

    private  String email;

    private byte[] imagem;

    public UsuarioModel(String nome, String email, byte[] imagem) {
        this.nome = nome;
        this.email = email;
        this.imagem = imagem;
    }



    public static List<UsuarioModel> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> new UsuarioModel(user.getNome(),
                user.getEmail(),user.getImagem())).collect(Collectors.toList());
    }

}
