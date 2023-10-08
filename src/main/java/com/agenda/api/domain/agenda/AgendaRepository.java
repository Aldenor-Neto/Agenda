package com.agenda.api.domain.agenda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    Page<Agenda> findAllByAtivoTrue(Pageable paginacao);

}