package com.mameie_fsm.gui.components.service;

import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import com.mameie_fsm.gui.components.standard.repository.StandardComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardComponentService {

    private final StandardComponentRepository standardComponentRepository;

    @Autowired
    public StandardComponentService(StandardComponentRepository standardComponentRepository) {
        this.standardComponentRepository = standardComponentRepository;
    }

    public List<StandardComponent> getAllStandardComponents() {
        return standardComponentRepository.findAll();
    }
}
