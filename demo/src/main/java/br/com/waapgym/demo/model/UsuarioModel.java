package br.com.waapgym.demo.model;

import br.com.waapgym.demo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {


    private String nome;

    private  String email;

    private String foto;


    public static List<UsuarioModel> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> new UsuarioModel(user.getNome(),user.getEmail(),user.getFoto())).collect(Collectors.toList());
    }

}
