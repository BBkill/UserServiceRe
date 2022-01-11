package aibless.userservicere.security;

import aibless.userservicere.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.attribute.UserPrincipal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class UserPrinciple implements UserDetails {

    private final String email;

    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    public static UserPrinciple create(User user) {
        String userRole = user.getRoles().iterator().next().toString();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userRole));
        return new UserPrinciple(user.getEmail(), user.getPassWord(), authorities);
    }

    public static UserPrinciple create(User user, Map<String, Object> attributes) {
        UserPrinciple userPrinciple = UserPrinciple.create(user);
        userPrinciple.setAttributes(attributes);
        return userPrinciple;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
