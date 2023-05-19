package quan.authservice.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import quan.authservice.service.AuthenticationService;
import quan.authservice.service.JwtService;
import quan.common.dto.auth.AuthenticationRequest;
import quan.common.dto.auth.AuthenticationResponse;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e){
            throw new RuntimeException("Bad Credentials");
        }
        return new AuthenticationResponse(jwtService.generateToken(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())));
    }
}
