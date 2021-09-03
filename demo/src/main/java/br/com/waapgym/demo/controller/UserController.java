package br.com.waapgym.demo.controller;


import br.com.waapgym.demo.model.UsuarioModel;

import br.com.waapgym.demo.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



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

    @PostMapping(value="create-user")
    public ResponseEntity cadastrar(@RequestPart(value = "file",required = false) MultipartFile file,@RequestPart(value = "input", required = false) String jsonText) throws JsonProcessingException {

        UsuarioModel jsonToModel = new ObjectMapper().readValue(jsonText, UsuarioModel.class);

        if (file != null) {
            try {
                String fileToString = Base64Utils.encodeToString(file.getBytes());
                jsonToModel.setFoto(fileToString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.userService.salvaUsuario(jsonToModel);
        return new ResponseEntity(HttpStatus.CREATED);

    }


    @GetMapping("/image/{nome}")
    @ResponseBody
    public String showImage(@PathVariable("nome") String nome){

        String imagemUrl = userService.ImageByNome(nome);
        return imagemUrl;
    }


}
