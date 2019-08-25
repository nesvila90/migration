package co.gov.sic.migration.service.state.application.repository;

import co.gov.sic.migration.persistence.entities.Estados;

import java.util.Optional;

public interface EstadosControl {

    Optional<Estados> findEstadosByCodigoEstado(final Integer codEstado);

}
