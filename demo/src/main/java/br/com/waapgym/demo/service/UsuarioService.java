package br.com.waapgym.demo.service;

import br.com.waapgym.demo.Usuario;
import br.com.waapgym.demo.model.UsuarioModel;
import br.com.waapgym.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository userRepository;

    @Transient
    public void salvaUsuario(UsuarioModel usuarioModel) {
        Usuario user = new Usuario();
        user.setNome(usuarioModel.getNome());
        user.setEmail(usuarioModel.getEmail());
        user.setFoto(usuarioModel.getFoto());
        user.setDataCriacao(LocalDateTime.now());
        userRepository.save(user);

    }

    public List<UsuarioModel> listarUsuarios(String nome) {
        if (nome == null) {
            List<Usuario> usuarios = userRepository.findAll();
            return UsuarioModel.converter(usuarios);
        }
        else {
            List<Usuario> usuarios = userRepository.findBynomeIgnoreCase(nome);
            return UsuarioModel.converter(usuarios);
        }
    }

    public String ImageByNome(String nome) {
        String imagemUrl = userRepository.findByNome(nome).getFoto();
        return imagemUrl;
    }



}
