package aziztechs.sn.appaxiomegroupbtpback.services.impl;

import aziztechs.sn.appaxiomegroupbtpback.config.jwt.JwtTokenProvider;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.LoginRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.LoginResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.AuthService;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.UtilisateurService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UtilisateurService utilisateurService;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider tokenProvider,
                           UtilisateurService utilisateurService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public LoginResponseDTO authenticate(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getMotDePasse()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return new LoginResponseDTO(jwt, utilisateurService.getCurrentUserProfile());
    }

    @Override
    public void logout(String token) {
        // Implémentation de l'invalidation du token
        tokenProvider.invalidateToken(token);
    }
}
