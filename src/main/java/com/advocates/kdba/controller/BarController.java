package com.advocates.kdba.controller;


import com.advocates.kdba.models.BarMembers;
import com.advocates.kdba.repository.BarMembersRepository;
import com.advocates.kdba.response.GenericResponse;
import com.advocates.kdba.service.BarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/kdba")
@RestController
public class BarController {


    @Autowired
    private BarService barService;

    @Autowired
    private BarMembersRepository barMembersRepository;

    private static final String AUTH_MECHANISM = "bearerAuth";

//    ****************/ JSON Input /*************************************************
//    @PostMapping("/barMember")
//    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Insert Bar Member", description = "Add Bar Member", tags = { "Bar Member" })
//    public GenericResponse createBarMember(@Valid @RequestBody BarMembers barMembers){
//
//        return barService.saveBarMember(barMembers);
//    }


    //    ****************/ Form Data /*************************************************
    @PostMapping("/barMember")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Insert Bar Member", description = "Add Bar Member", tags = { "Bar Member" })
    public GenericResponse createBarMember(@Valid @RequestBody MultipartFile file, @RequestParam String enrollmentNo,
                                           @RequestParam String enrollmentDate,@RequestParam String firstname,
                                           @RequestParam String dob,@RequestParam String gender,
                                           @RequestParam String mobile,@RequestParam String lfNumber,@RequestParam String admissionDate,
                                           @RequestParam String remarks, @RequestParam String cop) throws IOException {

        return barService.saveBarMember(file,enrollmentNo,enrollmentDate,firstname,dob,gender,
                                        mobile,lfNumber,admissionDate,remarks,cop);
    }


    @GetMapping("/getBarMembers")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Members ", description = "Get Members", tags = { "Bar Member" })
    public GenericResponse fetchMembers(){
        return barService.getMembers();
    }


    @PutMapping("/barMember")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Edit Bar Member", description = "Update Bar Member", tags = { "Bar Member" })
    public GenericResponse editMembers(@Valid @RequestBody MultipartFile file, @RequestParam String enrollmentNo,
                                       @RequestParam String enrollmentDate,@RequestParam String firstname,
                                       @RequestParam String dob,@RequestParam String gender,
                                       @RequestParam String mobile,@RequestParam String lfNumber,@RequestParam String admissionDate,
                                       @RequestParam String remarks, @RequestParam String cop,  @RequestParam String id) throws IOException {

        BarMembers existingMembers = barMembersRepository.findById(id).orElse(null);
        return barService.updateMembers(existingMembers,file,enrollmentNo,enrollmentDate,firstname,dob,gender,
                mobile,lfNumber,admissionDate,remarks,cop);
    }

    @DeleteMapping("/barMember/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Delete Bar Member", description = "Remove Bar Member", tags = { "Bar Member" })
    public GenericResponse removeMembers(@PathVariable String id ) {
        return barService.deleteMembers(id);
    }

}
