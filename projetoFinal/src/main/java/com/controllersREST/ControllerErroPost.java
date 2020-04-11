package com.controllersREST;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beans.*;
import com.entity.*;

@Controller

public class ControllerErroPost {
  @RequestMapping(method = RequestMethod.POST, value="/erro/post")
  @ResponseBody

  public RespostaRegistroErro registraErro(@RequestBody Erro erro) {
	  
	  	System.out.println("In registraErro");
	  	RespostaRegistroErro resposta = new RespostaRegistroErro();        
	  	RegistroErro.getInstance().add(erro);
        
		resposta.setTitulo(erro.getTitulo());
		resposta.setDetalhes(erro.getDetalhes());
		resposta.setOrigem(erro.getOrigem());
		resposta.setNivel(erro.getNivel());
		resposta.setIdUsuario(erro.getIdUsuario());
        return resposta;

}

}