package br.edu.uni7.restjpapostgres.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uni7.restjpapostgres.entity.Comment;
import br.edu.uni7.restjpapostgres.entity.Serie;
import br.edu.uni7.restjpapostgres.persistence.CommentRepository;
import br.edu.uni7.restjpapostgres.persistence.SerieRepository;

@RestController
public class SerieController {
	@Autowired
	private SerieRepository serieRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	// List All Series: GET /series
	// Save a Serie: POST /series
	// List All Commnents by Serie: GET /series/{id}/comments -> Ex.: GET /series/10/comments
	// Save a Comment: POST /series/{id}/comments -> Ex.: POST /series/10/comments
	
	@GetMapping("/series")
	public ResponseEntity<?> buscarSeries() {
		ResponseEntity<?> response = null;

		List<Serie> series = serieRepository.findAll();

		if (series != null) {
			if (series.isEmpty()) {
				response = new ResponseEntity<>(series, HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<>(series, HttpStatus.OK);
			}
		}

		return response;
	}

	@PostMapping("/series")
	public ResponseEntity<?> salvarSerie(@RequestBody Serie serie) {
		serie = serieRepository.save(serie);

		return new ResponseEntity<>(serie, HttpStatus.OK);
	}
	
	@GetMapping("/series/{serieId}/comments")
	public ResponseEntity<?> buscarComentariosPorSerie(
			@PathVariable(name = "serieId") Long serieId) {
		ResponseEntity<?> response = null;

		Optional<Serie> opt = serieRepository.findById(serieId);
		if (opt.isPresent()) {
			Serie serie = opt.get();
			List<Comment> comments = serie.getComments();
			
			if (comments.isEmpty()) {
				response = new ResponseEntity<>(comments, HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<>(comments, HttpStatus.OK);
			}
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}
	
	
	@PostMapping("/series/{serieId}/comments")
	public ResponseEntity<?> salvarComentario(
			@PathVariable(name = "serieId")Long serieId, 
			@RequestBody Comment comment) {
		
		Serie serie = new Serie();
		serie.setId(serieId);
		
		comment.setSerie(serie);
		
		comment = commentRepository.save(comment);

		return new ResponseEntity<>(comment, HttpStatus.OK);
	}
	
}
