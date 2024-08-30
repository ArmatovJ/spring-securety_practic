package kg.end_pont.springsecurety_practic.service.interfaces;

import java.util.List;

public interface EntityQuery<T> {
    T getEntityById(Long id);
    List<T> getEntitiesByName(String name);
    List<T> getAllEntities();
}
