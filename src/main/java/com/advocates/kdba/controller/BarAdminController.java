package com.advocates.kdba.controller;

import com.advocates.kdba.models.BarAdmin;
import com.advocates.kdba.repository.BarAdminRepository;
import com.advocates.kdba.response.GenericResponse;
import com.advocates.kdba.service.BarAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/kdba")
@RestController
public class BarAdminController {

    @Autowired
    private BarAdminService barAdminService;

    @Autowired
    private BarAdminRepository barAdminRepository;

    private static final String AUTH_MECHANISM = "bearerAuth";


    @GetMapping("/admin")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Admin", description = "Get Admin", tags = { "Kdba Admins" })
    public GenericResponse fetchAdminById(){
        return barAdminService.getAdmin();
    }

    @GetMapping("/admin/{email}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Admin By Email", description = "Find Records of Admin By Email", tags = { "Kdba Admins" })
    public GenericResponse fetchAdminByEmail(@PathVariable String email){
        return barAdminService.getAdminByEmail(email);
    }

    @PostMapping("/registerAdmin")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Register", description = "Register Admins", tags = { "Kdba Admins" })
    public GenericResponse register(@Valid @RequestBody BarAdmin barAdmin){
        return barAdminService.registerAdmin(barAdmin);
    }

    @PostMapping("/login")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Admin By email and Password", description = "Find Records of Admin By email and Password", tags = { "Kdba Admins" })
    public GenericResponse fetchAdminByEmailAndPass(@Valid @RequestBody BarAdmin barAdmin){
        return barAdminService.getAdminByEmailAndPass(barAdmin);
    }

    @PutMapping("/admin/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Update Admin", description = "Update/Modify Admin Record", tags = { "Kdba Admins" })
    public GenericResponse editAdmin(@RequestBody BarAdmin barAdmin,@PathVariable String id){
        BarAdmin existingAdmin = barAdminRepository.findById(id).orElse(null);
        return barAdminService.updateAdmin(existingAdmin,barAdmin);
    }

    @PutMapping("/adminPassword/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Update Admin Password", description = "Update/Modify Admin Password Record", tags = { "Kdba Admins" })
    public GenericResponse editAdminPassword(@RequestBody BarAdmin barAdmin,@PathVariable String id){
        BarAdmin existingAdmin = barAdminRepository.findById(id).orElse(null);
        return barAdminService.updateAdminPassword(existingAdmin,barAdmin);
    }

    @DeleteMapping("/admin/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Delete Admin", description = "Delete Admin By ID", tags = { "Kdba Admins" })
    public GenericResponse removeAdmin(@PathVariable String id ) {
        return barAdminService.deleteAdmin(id);
    }
}
