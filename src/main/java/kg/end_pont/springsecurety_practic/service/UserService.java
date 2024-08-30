package kg.end_pont.springsecurety_practic.service;

import kg.end_pont.springsecurety_practic.entity.RoleEntity;
import kg.end_pont.springsecurety_practic.entity.UserEntity;
import kg.end_pont.springsecurety_practic.repository.UserRepository;
import kg.end_pont.springsecurety_practic.service.interfaces.EntityDelete;
import kg.end_pont.springsecurety_practic.service.interfaces.EntityQuery;
import kg.end_pont.springsecurety_practic.service.interfaces.EntityRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements
        UserDetailsService,
        EntityDelete<UserEntity>,
        EntityQuery<UserEntity>,
        EntityRegister<UserEntity> {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Пользователь не найден"));
        return user;
    }

    @Transactional
    @Override
    public void deleteEntityById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserEntity getEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Пользователь не найден"));

    }

    @Override
    public List<UserEntity> getEntitiesByName(String name) {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserEntity> filteredEntities = new ArrayList<>();
        String lowerCaseName = name.toLowerCase();
        for (UserEntity userEntity : userEntities) {
            if (userEntity.getUsername().toLowerCase().contains(lowerCaseName)) {
                filteredEntities.add(userEntity);
            }
        }
        return filteredEntities;
    }

    @Override
    public List<UserEntity> getAllEntities() {
        return userRepository.findAll();
    }

    @Override
    public void registerNewEntity(UserEntity entity) {
        userRepository.save(entity);
    }
}
