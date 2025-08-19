package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;


import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.LoginRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentification", description = "API pour la gestion de l'authentification")
@RequestMapping("/api/auth")
public interface AuthController {

    @Operation(summary = "Connexion d'un utilisateur")
    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> authenticateUser(@RequestBody LoginRequestDTO loginRequest);

    @Operation(summary = "Déconnexion d'un utilisateur")
    @PostMapping("/logout")
    ResponseEntity<Void> logoutUser(@RequestHeader("Authorization") String token);
}
