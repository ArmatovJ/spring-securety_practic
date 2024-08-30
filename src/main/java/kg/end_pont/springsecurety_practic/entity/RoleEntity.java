package kg.end_pont.springsecurety_practic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity implements GrantedAuthority {


    @Column(name = "title")
    private String title;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m2m_users_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<UserEntity> users;

    @Override
    public String getAuthority() {
        return this.title;
    }

    public String getTitle() {
        return title;
    }

    public RoleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public RoleEntity setUsers(Set<UserEntity> users) {
        this.users = users;
        return this;
    }
}
