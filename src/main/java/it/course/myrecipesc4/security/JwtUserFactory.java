package it.course.myrecipesc4.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import it.course.myrecipesc4.entity.Authority;
import it.course.myrecipesc4.entity.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
        		user.getUserId(),
                user.getNickname(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnabled()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
