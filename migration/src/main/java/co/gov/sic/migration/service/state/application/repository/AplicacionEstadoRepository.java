package co.gov.sic.migration.service.state.application.repository;

import co.gov.sic.migration.persistence.entities.AplicacionEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AplicacionEstadoRepository extends JpaRepository<AplicacionEstado, Integer> {

    AplicacionEstado findTopByOrderByIdEjecucionDesc();

}
