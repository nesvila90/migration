package co.gov.sic.migration.service.state.execution.repository;

import co.gov.sic.migration.persistence.entities.Ejecucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EjecucionControl extends JpaRepository<Ejecucion, String> {

    @Query("select max(m.fechaRegistroAuditoria) from Ejecucion m ")
    Ejecucion findExecutionByFechaRegistroAuditoria();

}
