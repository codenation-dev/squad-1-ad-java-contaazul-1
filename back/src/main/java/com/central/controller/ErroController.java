package com.central.controller;


import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.central.entity.Erro;
import com.central.exception.ResourceNotFoundException;
import com.central.service.ErroService;


@CrossOrigin(origins = "*")
@RestController
public class ErroController {
	
	@Autowired
	private ErroService erroService;
	

	@GetMapping("/erro")
	public List<Erro> getAllErros() {
		return erroService.getAllErros();
	}
	
	@GetMapping("/erro/{id}")
	public Optional<Erro> getErro(@PathVariable Long id) {
		return erroService.findById(id);
	}
	
	@RequestMapping(path = "/erro/eventos/{titulo}", method = RequestMethod.GET)
	public Integer eventosErro(@PathVariable String titulo) {
		return erroService.frequenciaErros(titulo);
	}
	
	@PostMapping("/erro")
	public Erro registraErro(@Valid @RequestBody Erro erro) {	
		System.out.println("Novo erro: " + erro.getTitulo()); 
		return erroService.save(erro);

	}
	
	@PutMapping("/erro/{id}")
    public ResponseEntity<Erro> atualizaErro(@PathVariable(value = "id") Long erroId,
         @Valid @RequestBody Erro novoErro) throws ResourceNotFoundException{
		return erroService.atualizaErro(erroId, novoErro);
    }

    @DeleteMapping("/erro/{id}")
    public Map<String, Boolean> deletaErro(@PathVariable(value = "id") Long erroId)
         throws ResourceNotFoundException {
        return erroService.removeErro(erroId);
    }
    
	@PutMapping("/erro/arquivarErro/{listaErro}")
    public Boolean arquivarErro(@PathVariable(value = "listaErro") List<Long> listaErros
        ) throws ResourceNotFoundException{
		
		return erroService.arquivarErro(listaErros);
    }
	
    @DeleteMapping("/erro/deleteErros/{listaErros}")
    public Boolean deletaErros(@PathVariable(value = "listaErros") List<Long> listaErros)
         throws ResourceNotFoundException {
		
    	return erroService.deletaErros(listaErros);
    }
}

