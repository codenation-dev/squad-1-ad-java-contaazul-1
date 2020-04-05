package com.bhaiti.kela.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhaiti.kela.beans.Erro;
import com.bhaiti.kela.beans.RegistroErro;

@Controller
public class ControllerErroGet {
	@RequestMapping(method = RequestMethod.GET, value = "/get")
	@ResponseBody
	public List<Erro> getAllErros() {
		return RegistroErro.getInstance().getErros();
	}

}