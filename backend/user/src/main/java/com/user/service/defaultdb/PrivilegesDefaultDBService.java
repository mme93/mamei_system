package com.user.service.defaultdb;

import com.user.repository.PrivilegesEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegesDefaultDBService {

    private final PrivilegesEntityRepository privilegesEntityRepository;

    @Autowired
    public PrivilegesDefaultDBService(PrivilegesEntityRepository privilegesEntityRepository) {
        this.privilegesEntityRepository = privilegesEntityRepository;
    }

    public void createDefaultDataSet() {

    }

    public void deleteAllData() {
        privilegesEntityRepository.deleteAll();
    }

    public void deleteDefaultDataset() {
    }
}
