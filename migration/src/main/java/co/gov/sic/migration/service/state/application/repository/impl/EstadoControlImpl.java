package co.gov.sic.migration.service.state.application.repository.impl;

import co.gov.sic.migration.persistence.entities.Estados;
import co.gov.sic.migration.service.state.application.repository.EstadosControl;
import co.gov.sic.migration.service.state.application.repository.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class EstadoControlImpl implements EstadosControl {

    private final EstadosRepository repository;

    @Autowired
    public EstadoControlImpl(EstadosRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Estados> findEstadosByCodigoEstado(Integer codEstado) {
        return repository.findByCodigoEstado(codEstado);
    }
}

