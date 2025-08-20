package aziztechs.sn.appaxiomegroupbtpback.config;

import aziztechs.sn.appaxiomegroupbtpback.entities.*;
import aziztechs.sn.appaxiomegroupbtpback.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

/**
 * Classe d'initialisation des données pour le développement et les tests
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ChantierRepository chantierRepository;
    private final FournisseurRepository fournisseurRepository;
    private final BudgetRepository budgetRepository;
    private final JalonRepository jalonRepository;
    private final IncidentRepository incidentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Vérifier si des données existent déjà
        if (roleRepository.count() > 0) {
            log.info("La base de données contient déjà des données, initialisation ignorée.");
            return;
        }

        log.info("Initialisation des données de démonstration...");

        // Création des rôles
        initRoles();

        // Création des utilisateurs
        initUtilisateurs();

        // Création des fournisseurs
        initFournisseurs();

        // Création des chantiers
        initChantiers();

        log.info("Initialisation des données terminée avec succès.");
    }

    private void initRoles() {
        log.info("Création des rôles...");

        // Rôle Administrateur
        Role adminRole = new Role();
        adminRole.setNom("ROLE_ADMIN");
        adminRole.setPermissions(new HashSet<>(Arrays.asList(
                "user:create", "user:read", "user:update", "user:delete",
                "chantier:create", "chantier:read", "chantier:update", "chantier:delete",
                "budget:create", "budget:read", "budget:update", "budget:delete",
                "facture:create", "facture:read", "facture:update", "facture:delete",
                "fournisseur:create", "fournisseur:read", "fournisseur:update", "fournisseur:delete"
        )));
        roleRepository.save(adminRole);

        // Rôle Chef de Projet
        Role chefProjetRole = new Role();
        chefProjetRole.setNom("ROLE_CHEF_PROJET");
        chefProjetRole.setPermissions(new HashSet<>(Arrays.asList(
                "chantier:create", "chantier:read", "chantier:update",
                "budget:read", "budget:update",
                "facture:create", "facture:read", "facture:update",
                "fournisseur:read"
        )));
        roleRepository.save(chefProjetRole);

        // Rôle Comptable
        Role comptableRole = new Role();
        comptableRole.setNom("ROLE_COMPTABLE");
        comptableRole.setPermissions(new HashSet<>(Arrays.asList(
                "budget:read", "budget:update",
                "facture:create", "facture:read", "facture:update",
                "fournisseur:read"
        )));
        roleRepository.save(comptableRole);

        // Rôle Technicien
        Role technicienRole = new Role();
        technicienRole.setNom("ROLE_TECHNICIEN");
        technicienRole.setPermissions(new HashSet<>(Arrays.asList(
                "chantier:read",
                "incident:create", "incident:read", "incident:update"
        )));
        roleRepository.save(technicienRole);

        log.info("Création des rôles terminée.");
    }

    private void initUtilisateurs() {
        log.info("Création des utilisateurs...");

        // Récupération des rôles
        Role adminRole = roleRepository.findByNom("ROLE_ADMIN").orElseThrow();
        Role chefProjetRole = roleRepository.findByNom("ROLE_CHEF_PROJET").orElseThrow();
        Role comptableRole = roleRepository.findByNom("ROLE_COMPTABLE").orElseThrow();
        Role technicienRole = roleRepository.findByNom("ROLE_TECHNICIEN").orElseThrow();

        // Administrateur
        Utilisateur admin = new Utilisateur();
        admin.setNom("Admin");
        admin.setPrenom("System");
        admin.setEmail("admin@axiomegroup.sn");
        admin.setMotDePasse(passwordEncoder.encode("Admin@123"));
        admin.setStatut("ACTIF");
        admin.setRoles(new HashSet<>(Collections.singletonList(adminRole)));
        utilisateurRepository.save(admin);

        // Chef de projet
        Utilisateur chefProjet = new Utilisateur();
        chefProjet.setNom("Diop");
        chefProjet.setPrenom("Amadou");
        chefProjet.setEmail("amadou.diop@axiomegroup.sn");
        chefProjet.setMotDePasse(passwordEncoder.encode("Amadou@123"));
        chefProjet.setStatut("ACTIF");
        chefProjet.setRoles(new HashSet<>(Collections.singletonList(chefProjetRole)));
        utilisateurRepository.save(chefProjet);

        // Comptable
        Utilisateur comptable = new Utilisateur();
        comptable.setNom("Ndiaye");
        comptable.setPrenom("Fatou");
        comptable.setEmail("fatou.ndiaye@axiomegroup.sn");
        comptable.setMotDePasse(passwordEncoder.encode("Fatou@123"));
        comptable.setStatut("ACTIF");
        comptable.setRoles(new HashSet<>(Collections.singletonList(comptableRole)));
        utilisateurRepository.save(comptable);

        // Technicien
        Utilisateur technicien = new Utilisateur();
        technicien.setNom("Sow");
        technicien.setPrenom("Ibrahima");
        technicien.setEmail("ibrahima.sow@axiomegroup.sn");
        technicien.setMotDePasse(passwordEncoder.encode("Ibrahima@123"));
        technicien.setStatut("ACTIF");
        technicien.setRoles(new HashSet<>(Collections.singletonList(technicienRole)));
        utilisateurRepository.save(technicien);

        log.info("Création des utilisateurs terminée.");
    }

    private void initFournisseurs() {
        log.info("Création des fournisseurs...");

        // Fournisseur 1
        Fournisseur fournisseur1 = new Fournisseur();
        fournisseur1.setRaisonSociale("Ciment du Sahel");
        fournisseur1.setAdresse("Zone industrielle, Dakar");
        fournisseur1.setContact("+221 33 889 45 67");
        fournisseur1.setNotation(4);
        fournisseurRepository.save(fournisseur1);

        // Fournisseur 2
        Fournisseur fournisseur2 = new Fournisseur();
        fournisseur2.setRaisonSociale("Fer du Sénégal");
        fournisseur2.setAdresse("Rufisque, Dakar");
        fournisseur2.setContact("+221 33 836 78 90");
        fournisseur2.setNotation(3);
        fournisseurRepository.save(fournisseur2);

        // Fournisseur 3
        Fournisseur fournisseur3 = new Fournisseur();
        fournisseur3.setRaisonSociale("Matériaux BTP Plus");
        fournisseur3.setAdresse("Yoff, Dakar");
        fournisseur3.setContact("+221 33 820 12 34");
        fournisseur3.setNotation(5);
        fournisseurRepository.save(fournisseur3);

        // Fournisseur 4
        Fournisseur fournisseur4 = new Fournisseur();
        fournisseur4.setRaisonSociale("Sénégal Équipements");
        fournisseur4.setAdresse("Parcelles Assainies, Dakar");
        fournisseur4.setContact("+221 33 855 67 89");
        fournisseur4.setNotation(4);
        fournisseurRepository.save(fournisseur4);

        log.info("Création des fournisseurs terminée.");
    }

    private void initChantiers() {
        log.info("Création des chantiers...");

        // Chantier 1
        Chantier chantier1 = new Chantier();
        chantier1.setReference("CH-2025-001");
        chantier1.setClient("Ministère de l'Urbanisme");
        chantier1.setLocalisation("Diamniadio");
        chantier1.setBudgetInitial(500000000.0);
        chantier1.setDateDebut(LocalDate.now());
        chantier1.setDateFin(LocalDate.now().plusMonths(12));
        chantier1.setStatut("PLANIFIE");
        chantierRepository.save(chantier1);

        // Budget pour Chantier 1
        Budget budget1 = new Budget();
        budget1.setMontantPrevu(500000000.0);
        budget1.setMontantEngage(0.0);
        budget1.setMontantRealise(0.0);
        budget1.setChantier(chantier1);
        budgetRepository.save(budget1);
        
        chantier1.setBudget(budget1);
        chantierRepository.save(chantier1);

        // Jalons pour Chantier 1
        Jalon jalon1 = new Jalon();
        jalon1.setNom("Fondations");
        jalon1.setDescription("Réalisation des fondations");
        jalon1.setDatePrevue(LocalDate.now().plusMonths(2));
        jalon1.setStatut("PLANIFIE");
        jalon1.setChantier(chantier1);
        jalonRepository.save(jalon1);

        Jalon jalon2 = new Jalon();
        jalon2.setNom("Gros œuvre");
        jalon2.setDescription("Réalisation du gros œuvre");
        jalon2.setDatePrevue(LocalDate.now().plusMonths(6));
        jalon2.setStatut("PLANIFIE");
        jalon2.setChantier(chantier1);
        jalonRepository.save(jalon2);

        // Chantier 2
        Chantier chantier2 = new Chantier();
        chantier2.setReference("CH-2025-002");
        chantier2.setClient("Société Immobilière du Cap-Vert");
        chantier2.setLocalisation("Dakar Plateau");
        chantier2.setBudgetInitial(300000000.0);
        chantier2.setDateDebut(LocalDate.now().minusMonths(2));
        chantier2.setDateFin(LocalDate.now().plusMonths(8));
        chantier2.setStatut("EN_COURS");
        chantierRepository.save(chantier2);

        // Budget pour Chantier 2
        Budget budget2 = new Budget();
        budget2.setMontantPrevu(300000000.0);
        budget2.setMontantEngage(75000000.0);
        budget2.setMontantRealise(50000000.0);
        budget2.setChantier(chantier2);
        budgetRepository.save(budget2);
        
        chantier2.setBudget(budget2);
        chantierRepository.save(chantier2);

        // Jalons pour Chantier 2
        Jalon jalon3 = new Jalon();
        jalon3.setNom("Fondations");
        jalon3.setDescription("Réalisation des fondations");
        jalon3.setDatePrevue(LocalDate.now().minusMonths(1));
        jalon3.setDateReelle(LocalDate.now().minusMonths(1).plusDays(5));
        jalon3.setStatut("TERMINE");
        jalon3.setChantier(chantier2);
        jalonRepository.save(jalon3);

        Jalon jalon4 = new Jalon();
        jalon4.setNom("Gros œuvre");
        jalon4.setDescription("Réalisation du gros œuvre");
        jalon4.setDatePrevue(LocalDate.now().plusMonths(2));
        jalon4.setStatut("EN_COURS");
        jalon4.setChantier(chantier2);
        jalonRepository.save(jalon4);

        // Incidents pour Chantier 2
        Incident incident1 = new Incident();
        incident1.setTitre("Retard livraison ciment");
        incident1.setDescription("Retard de livraison du ciment par le fournisseur");
        incident1.setDateSignalement(LocalDate.now().minusDays(15));
        incident1.setStatut("RESOLU");
        incident1.setType("APPROVISIONNEMENT");
        incident1.setChantier(chantier2);
        incidentRepository.save(incident1);

        Incident incident2 = new Incident();
        incident2.setTitre("Problème électrique");
        incident2.setDescription("Problème d'alimentation électrique sur le chantier");
        incident2.setDateSignalement(LocalDate.now().minusDays(5));
        incident2.setStatut("EN_COURS");
        incident2.setType("TECHNIQUE");
        incident2.setChantier(chantier2);
        incidentRepository.save(incident2);

        log.info("Création des chantiers terminée.");
    }
}

