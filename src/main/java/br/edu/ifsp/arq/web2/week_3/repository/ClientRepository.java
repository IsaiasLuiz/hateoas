package br.edu.ifsp.arq.web2.week_3.repository;

import br.edu.ifsp.arq.web2.week_3.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
