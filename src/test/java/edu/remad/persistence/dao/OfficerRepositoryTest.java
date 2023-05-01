package edu.remad.persistence.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import edu.remad.persistence.entities.Officer;
import edu.remad.persistence.entities.Rank;

@SpringBootTest
@Transactional
public class OfficerRepositoryTest {
	
	@Autowired
	private OfficerRepository dao;
	
	@Autowired
	private JdbcTemplate template;
	
	private List<Integer> getIds() {
		return template.query("select id from officers", (rs, num) -> rs.getInt("id"));
	}
	
	@Test
	void findAllByRankAndLastNameContaining() {
		List<Officer> officers = dao.findAllByRankAndLastNameContaining(Rank.CAPTAIN, "i");
		officers.forEach(System.out::println);
	}

	@Test
	public void testSave() {
		Officer officer = new Officer(Rank.LIEUTENANT, "wesley", "Crusher");
		officer = dao.save(officer);
		assertNotNull(officer.getId());
	}
	
	@Test
	public void findOneThatExists() {
		getIds().forEach(id ->  {
			Optional<Officer> officer = dao.findById(id);
			assertTrue(officer.isPresent());
			assertEquals(id, officer.get().getId());
		});
	}
	
	@Test
	@Transactional(readOnly = true)
	public void findOneThatDoesNotExist( ) {
		Optional<Officer> officer = dao.findById(999);
		assertFalse(officer.isPresent());
	}
	
	@Test
	public void findAll( ) {
		List<String> dbNames = dao.findAll().stream().map(Officer::getLastName).collect(Collectors.toList());
		assertThat(dbNames, containsInAnyOrder("Archer","Janeway", "Kirk", "Picard", "Sisko"));
	}
	
	@Test
	public void count() {
		assertEquals(5, dao.count());
	}
	
	@Test
	public void delete() {
		getIds().forEach(id -> {
			Optional<Officer> officer = dao.findById(id);
			assertTrue(officer.isPresent());
			dao.delete(officer.get());
		});
		assertEquals(0, dao.count());
	}
	
	@Test
	public void existsById() {
		getIds().forEach(id -> assertTrue(dao.existsById(id)));
	}
}
