package org.serratec.curriculos.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculos.dto.CandidatoDto;
import org.serratec.curriculos.model.Escolaridade;
import org.serratec.curriculos.model.VagaDesejada;
import org.serratec.curriculos.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
	@Autowired
	private CandidatoService candidatoService;
	
	@GetMapping
	public List<CandidatoDto> obterTodos() {
		return candidatoService.obterTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CandidatoDto> obterPorId(@PathVariable Long id) {
		Optional<CandidatoDto> dto = candidatoService.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@GetMapping("/vaga/{vagaDesejada}")
    public List<CandidatoDto> obterPorVagaDesejada(@PathVariable VagaDesejada vagaDesejada) {
        return candidatoService.obterPorVagaDesejada(vagaDesejada);
    }

    @GetMapping("/escolaridade/{escolaridade}")
    public List<CandidatoDto> obterPorEscolaridade(@PathVariable Escolaridade escolaridade) {
        return candidatoService.obterPorEscolaridade(escolaridade);
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoDto cadastrarCandidato(@RequestBody CandidatoDto dto) {
		return candidatoService.salvarCandidato(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaCandidato(@PathVariable Long id) {
		if (!candidatoService.apagarCandidato(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CandidatoDto> alterarCandidato(@PathVariable Long id, @RequestBody CandidatoDto dto) {
		Optional<CandidatoDto> candidatoAlterado = candidatoService.alterarCandidato(id, dto);
		if (!candidatoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidatoAlterado.get());
	}
}
