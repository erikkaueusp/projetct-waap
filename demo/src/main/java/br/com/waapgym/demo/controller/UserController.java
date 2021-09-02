package br.com.waapgym.demo.controller;


import br.com.waapgym.demo.model.UsuarioModel;

import br.com.waapgym.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsuarioService userService;


    @GetMapping
    public List<UsuarioModel> usuarios(String nome) {
        List<UsuarioModel> usuarios = userService.listarUsuarios(nome);
        return usuarios;
    }

    @PostMapping("/create-user")
    public ResponseEntity cadastrar(@RequestParam(value = "image",required = false) MultipartFile file,
                                    @RequestBody UsuarioModel usuarioModel) {

        if (file != null) {
            try {
                usuarioModel.setImagem(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.userService.salvaUsuario(usuarioModel);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @GetMapping("/image/{nome}")
    @ResponseBody
    public byte showImage(@PathVariable("nome") String nome){

        byte imagem = userService.ImageByNome(nome);
        return imagem;
    }


}
