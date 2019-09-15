package br.com.spring.dev.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.dev.model.Writer;
import br.com.spring.dev.service.WriterService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "writers")
public class WriterEndpoint {

	@Autowired
	private WriterService writerService;

	@GetMapping(path="")
	public List<Writer> listAllWriters() {
		return this.writerService.listAllWriterss();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Writer> getWriterById(@PathVariable("id") Long id, @RequestBody Writer writer,
			@AuthenticationPrincipal UserDetails userDetails) {
		this.writerService.verifyIfWriterExists(id);
		Writer writer2 = writerService.findWriterById(id, writer);
		return new ResponseEntity<>(writer2, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Writer> saveNewWriter(@RequestBody Writer writer) {
		return new ResponseEntity<>(writerService.saveWriter(writer), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Writer> deleleWriter(@PathVariable Long id) {
		this.writerService.verifyIfWriterExists(id);
		this.writerService.deleteWriter(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "")
	public ResponseEntity<Writer> updateWriter(@RequestBody Writer writer) {
		this.writerService.verifyIfWriterExists(writer.getId());
		this.writerService.updateWriter(writer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
