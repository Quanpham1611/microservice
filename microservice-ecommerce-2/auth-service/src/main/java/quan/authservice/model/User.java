package quan.authservice.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import quan.authservice.dto.response.CustomerResponse;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class User implements UserDetails {
    private final CustomerResponse customerResponse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(customerResponse.getRole().name()));
    }

    @Override
    public String getPassword() {
        return customerResponse.getPassword();
    }

    @Override
    public String getUsername() {
        return customerResponse.getUsername();
    }
    //kiểm tra tài khoản nguời dùng còn hạn truy cập không
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //kiểm tra tài khoản có bi khóa không
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //kiểm tra mật khẩu còn hạn không
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //kiểm tra tài khoản được kích hoạt chưa
    @Override
    public boolean isEnabled() {
        return true;
    }
}
