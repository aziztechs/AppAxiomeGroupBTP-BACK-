package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;


import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.AuthController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.LoginRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.LoginResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<LoginResponseDTO> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> logoutUser(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok().build();
    }
}