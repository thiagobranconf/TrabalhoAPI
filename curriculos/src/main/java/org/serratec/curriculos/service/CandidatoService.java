package org.serratec.curriculos.service;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculos.dto.CandidatoDto;
import org.serratec.curriculos.model.Candidato;
import org.serratec.curriculos.model.Escolaridade;
import org.serratec.curriculos.model.VagaDesejada;
import org.serratec.curriculos.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	public List<CandidatoDto> obterTodos() {
		return candidatoRepository.findAll().stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
	
	public Optional<CandidatoDto> obterPorId(Long id) {
		if (!candidatoRepository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(CandidatoDto.toDto(candidatoRepository.findById(id).get()));
	}
	
	public CandidatoDto salvarCandidato(CandidatoDto dto) {
		Candidato candidatoEntity = candidatoRepository.save(dto.toEntity());
		return CandidatoDto.toDto(candidatoEntity);
	}
	
	public boolean apagarCandidato(Long id) {
		if (!candidatoRepository.existsById(id)) {
			return false;
		}
		candidatoRepository.deleteById(id);
		return true;
	}
	
	public Optional<CandidatoDto> alterarCandidato(Long id, CandidatoDto dto) {
		if (!candidatoRepository.existsById(id)) {
			return Optional.empty();
		}
		Candidato candidatoEntity = dto.toEntity();
		candidatoEntity.setId(id);
		candidatoRepository.save(candidatoEntity);
		return Optional.of(CandidatoDto.toDto(candidatoEntity));
	}
	
	public List<CandidatoDto> obterPorVagaDesejada(VagaDesejada vagaDesejada) {
		  List<Candidato> candidatos = candidatoRepository.findByVagaDesejada(vagaDesejada);
		  return candidatos.stream().map(c -> CandidatoDto.toDto(c)).toList();
		 }

	public List<CandidatoDto> obterPorEscolaridade(Escolaridade escolaridade) {
		List<Candidato> candidatos = candidatoRepository.findByEscolaridade(escolaridade);
		return candidatos.stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
}
