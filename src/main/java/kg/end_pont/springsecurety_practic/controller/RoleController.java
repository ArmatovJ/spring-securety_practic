package kg.end_pont.springsecurety_practic.controller;


import kg.end_pont.springsecurety_practic.entity.RoleEntity;
import kg.end_pont.springsecurety_practic.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/get_all_roles")
    public List<RoleEntity> getAllRoles() {
        return roleService.getAllEntities();
    }

    @GetMapping("/get_roles_by_name")
    public List<RoleEntity> getRolesByName(@RequestParam String name) {
        return roleService.getEntitiesByName(name);
    }

    @PostMapping("/add_role")
    public void addRole(@RequestBody RoleEntity role) {
        roleService.registerNewEntity(role);
    }

    @DeleteMapping("/delete_by_id")
    public void deleteRoleById(@RequestParam Long id) {
        roleService.deleteEntityById(id);
    }



}
