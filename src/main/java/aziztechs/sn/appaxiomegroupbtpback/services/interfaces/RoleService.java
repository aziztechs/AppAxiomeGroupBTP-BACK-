package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.RoleRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO roleDTO);
    RoleResponseDTO updateRole(Long id, RoleRequestDTO roleDTO);
    void deleteRole(Long id);
    RoleResponseDTO getRoleById(Long id);
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO addPermissionsToRole(Long roleId, List<String> permissions);
    RoleResponseDTO removePermissionsFromRole(Long roleId, List<String> permissions);
}
