package br.com.spring.dev;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.spring.dev.model.Writer;
import br.com.spring.dev.repository.WriterRepository;

/**
 * @author Bros
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class WriterRepositoryTest {
	
	@Autowired
	private WriterRepository writerRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	@Test
	public void createShouldPersistData() {
		Writer writer = new Writer( 1L , "Luiz", "luiz@luiz.com.br");
		this.writerRepository.save(writer);
		Assertions.assertThat(writer.getId()).isNotNull();
		Assertions.assertThat(writer.getName()).isEqualTo("Luiz");
		Assertions.assertThat(writer.getEmail()).isEqualTo("luiz@luiz.com.br");	

	}
	
	@Test
	public void deleteShouldRemoveData() {
		Writer writer = new Writer(1L , "Luiz", "luiz@luiz.com.br");
		this.writerRepository.save(writer);
		writerRepository.delete(writer);
		assertThat(writerRepository.findOne(writer.getId())).isNull();
	}
	
	

}
