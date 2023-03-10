package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table (name = "t_users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {
    @Column(name = "email")
    private String email;
    @Column (name = "password")
    private String password;
    @Column (name = "full_name")
    private String full_name;
    @ManyToMany(fetch = FetchType.EAGER)
    @Column (name = "permissions")
    private List<Permission> permissions;

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
