package edu.remad.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.remad.persistence.entities.Officer;
import edu.remad.persistence.entities.Rank;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
	List<Officer> findAllByRankAndLastNameContaining(Rank rank, String string);
}
