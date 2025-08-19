package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.LoginRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO authenticate(LoginRequestDTO loginRequest);
    void logout(String token);
}
