package hotelbooking.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyUserSec extends User {
    private String id;
    private String role;


    public MyUserSec(String email, String password, String id, String role, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.id = id;
        this.role = role;
    }

    public MyUserSec(String email, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getId() {
        return id;
    }

    public MyUserSec setId(String id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public MyUserSec setRole(String role) {
        this.role = role;
        return this;
    }

}
