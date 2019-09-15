package br.com.spring.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.dev.error.ResourceNotFoundException;
import br.com.spring.dev.model.Writer;
import br.com.spring.dev.repository.WriterRepository;

@Service
public class WriterService {

	@Autowired
	private WriterRepository writerRepository;

	public List<Writer> listAllWriterss() {
		return this.writerRepository.findAll();
	}

	public Writer findWriterById(Long id, Writer writer) {
		return this.writerRepository.findOne(id);
	}

	public Writer saveWriter(Writer writer) {
		return this.writerRepository.save(writer);
	}

	public void deleteWriter(Long id) {
		this.writerRepository.delete(id);
		return;
	}

	public void updateWriter(Writer writer) {
		this.writerRepository.save(writer);
	}

	public void verifyIfWriterExists(Long id) {
		if (this.writerRepository.findOne(id) == null)
			throw new ResourceNotFoundException("Writer not found for Id" + " " + id);
	}

}
