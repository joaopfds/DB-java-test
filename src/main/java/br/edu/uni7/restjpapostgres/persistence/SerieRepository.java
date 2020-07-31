package br.edu.uni7.restjpapostgres.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uni7.restjpapostgres.entity.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>{

}
