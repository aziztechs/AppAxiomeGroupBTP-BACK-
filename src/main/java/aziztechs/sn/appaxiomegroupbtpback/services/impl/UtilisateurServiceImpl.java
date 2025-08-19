package aziztechs.sn.appaxiomegroupbtpback.services.impl;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.UtilisateurRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.UtilisateurResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.Chantier;
import aziztechs.sn.appaxiomegroupbtpback.entities.Role;
import aziztechs.sn.appaxiomegroupbtpback.entities.Utilisateur;
import aziztechs.sn.appaxiomegroupbtpback.repositories.ChantierRepository;
import aziztechs.sn.appaxiomegroupbtpback.repositories.RoleRepository;
import aziztechs.sn.appaxiomegroupbtpback.repositories.UtilisateurRepository;
import aziztechs.sn.appaxiomegroupbtpback.exception.ResourceNotFoundException;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.UtilisateurService;
import aziztechs.sn.appaxiomegroupbtpback.utils.DtoMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final ChantierRepository chantierRepository;
    private final DtoMapper dtoMapper;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  RoleRepository roleRepository,
                                  ChantierRepository chantierRepository,
                                  DtoMapper dtoMapper,
                                  PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.chantierRepository = chantierRepository;
        this.dtoMapper = dtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UtilisateurResponseDTO createUtilisateur(UtilisateurRequestDTO utilisateurDTO) {
        Utilisateur utilisateur = dtoMapper.mapToEntity(utilisateurDTO, Utilisateur.class);
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        utilisateur.setStatut("ACTIF");

        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return dtoMapper.mapToDto(savedUtilisateur, UtilisateurResponseDTO.class);
    }

    @Override
    @Transactional
    public UtilisateurResponseDTO updateUtilisateur(Long id, UtilisateurRequestDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));

        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setEmail(utilisateurDTO.getEmail());

        if (utilisateurDTO.getMotDePasse() != null && !utilisateurDTO.getMotDePasse().isEmpty()) {
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        }

        utilisateur.setStatut(utilisateurDTO.getStatut());

        Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
        return dtoMapper.mapToDto(updatedUtilisateur, UtilisateurResponseDTO.class);
    }

    @Override
    @Transactional
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));

        utilisateur.setStatut("INACTIF");
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public UtilisateurResponseDTO getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));

        return dtoMapper.mapToDto(utilisateur, UtilisateurResponseDTO.class);
    }

    @Override
    public List<UtilisateurResponseDTO> getAllUtilisateurs() {
        return utilisateurRepository.findAll().stream()
                .map(utilisateur -> dtoMapper.mapToDto(utilisateur, UtilisateurResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UtilisateurResponseDTO assignRolesToUtilisateur(Long utilisateurId, List<Long> roleIds) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", utilisateurId));

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        utilisateur.setRoles(roles);

        Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
        return dtoMapper.mapToDto(updatedUtilisateur, UtilisateurResponseDTO.class);
    }

    @Override
    public UtilisateurResponseDTO getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "email", email));

        return dtoMapper.mapToDto(utilisateur, UtilisateurResponseDTO.class);
    }

    @Override
    public Chantier getChantierByReference(String reference) {
        return chantierRepository.findByReference(reference)
                .orElseThrow(() -> new ResourceNotFoundException("Chantier", "référence", reference));
    }
}