package quan.authservice.service;

import quan.common.dto.auth.AuthenticationRequest;
import quan.common.dto.auth.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
