package br.edu.ifsp.arq.web2.repository;

import br.edu.ifsp.arq.web2.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
