package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.RoleController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.RoleRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.RoleResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleResponseDTO> createRole(RoleRequestDTO roleDTO) {
        RoleResponseDTO createdRole = roleService.createRole(roleDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RoleResponseDTO> updateRole(Long id, RoleRequestDTO roleDTO) {
        RoleResponseDTO updatedRole = roleService.updateRole(id, roleDTO);
        return ResponseEntity.ok(updatedRole);
    }

    @Override
    public ResponseEntity<Void> deleteRole(Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RoleResponseDTO> getRoleById(Long id) {
        RoleResponseDTO role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @Override
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @Override
    public ResponseEntity<RoleResponseDTO> addPermissionsToRole(Long roleId, List<String> permissions) {
        RoleResponseDTO updatedRole = roleService.addPermissionsToRole(roleId, permissions);
        return ResponseEntity.ok(updatedRole);
    }

    @Override
    public ResponseEntity<RoleResponseDTO> removePermissionsFromRole(Long roleId, List<String> permissions) {
        RoleResponseDTO updatedRole = roleService.removePermissionsFromRole(roleId, permissions);
        return ResponseEntity.ok(updatedRole);
    }
}

