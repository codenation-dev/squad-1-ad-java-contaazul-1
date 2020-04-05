package com.bhaiti.kela.beans;

import java.util.ArrayList;

import java.util.List;

public class RegistroErro {
	
	private List<Erro> listaErros;
	private static RegistroErro reg = null;
	private RegistroErro(){
		listaErros = new ArrayList<Erro>();
	}

	public static RegistroErro getInstance() {
		if(reg == null) {
			reg = new RegistroErro();
			return reg;
		}
		else {
			return reg;
		}
	}

	public void add(Erro std) {

		listaErros.add(std);

	}

	public String atualizaErro(Erro e1) {
		for(Erro e2 : listaErros)
		{
			if(e1.getId() == e2.getId()) {
				listaErros.set(listaErros.indexOf(e2), e1);
				return "Update successful";
			}
		}
		return "Update un-successful";

	}

	public String deletaErro(Erro e1) {
		for(Erro e2 : listaErros)
		{
			if(e1.getId() == e2.getId()) {
				listaErros.remove(e2);
				return "Delete successful";
			}
		}
		return "Delete un-successful";
	}

	public List<Erro> getErros() {
		return listaErros;

	}

}