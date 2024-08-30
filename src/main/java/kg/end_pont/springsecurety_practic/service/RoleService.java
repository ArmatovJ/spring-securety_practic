package kg.end_pont.springsecurety_practic.service;

import kg.end_pont.springsecurety_practic.entity.RoleEntity;
import kg.end_pont.springsecurety_practic.entity.UserEntity;
import kg.end_pont.springsecurety_practic.repository.RoleRepository;
import kg.end_pont.springsecurety_practic.service.interfaces.EntityDelete;
import kg.end_pont.springsecurety_practic.service.interfaces.EntityQuery;
import kg.end_pont.springsecurety_practic.service.interfaces.EntityRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements
        EntityRegister<RoleEntity>,
        EntityQuery<RoleEntity>,
        EntityDelete<RoleEntity> {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public void deleteEntityById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RoleEntity getEntityById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<RoleEntity> getEntitiesByName(String name) {
        List<RoleEntity> roleEntities = repository.findAll();
        List<RoleEntity> filteredEntities = new ArrayList<>();
        String lowerCaseName = name.toLowerCase();
        for (RoleEntity entity : roleEntities) {
            if (entity.getTitle().toLowerCase().contains(lowerCaseName)) {
                filteredEntities.add(entity);
            }
        }
        return filteredEntities;
    }

    @Override
    public List<RoleEntity> getAllEntities() {
        return repository.findAll();
    }

    @Override
    public void registerNewEntity(RoleEntity entity) {
        repository.save(entity);
    }
}