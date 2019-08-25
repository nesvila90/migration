package co.gov.sic.migration.service.state.application.repository;

import co.gov.sic.migration.persistence.entities.Estados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadosRepository extends JpaRepository<Estados, Integer> {

    @Query("SELECT e from Estados e where e.codEstado = :COD_ESTADO")
    Optional<Estados> findByCodigoEstado(@Param("COD_ESTADO") Integer codEstado);

}
