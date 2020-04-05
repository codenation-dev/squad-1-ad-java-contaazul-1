package com.bhaiti.kela.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bhaiti.kela.beans.*;

@Controller

public class ControllerErroPost {
  @RequestMapping(method = RequestMethod.POST, value="/post")
  @ResponseBody

  public RespostaRegistroErro registraErro(@RequestBody Erro erro) {
	  
	  	System.out.println("In registraErro");
	  	RespostaRegistroErro resposta = new RespostaRegistroErro();        
	  	RegistroErro.getInstance().add(erro);
        
        resposta.setDescricao(erro.getDescricao());
        resposta.setOrigem(erro.getOrigem());
        resposta.setData(erro.getData());
        resposta.setNivel(erro.getNivel());
        resposta.setStatus("Successful");
        return resposta;

}

}