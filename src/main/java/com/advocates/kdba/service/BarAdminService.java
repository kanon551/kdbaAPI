package com.advocates.kdba.service;

import com.advocates.kdba.models.BarAdmin;
import com.advocates.kdba.models.BarMembers;
import com.advocates.kdba.repository.BarAdminRepository;
import com.advocates.kdba.response.GenericResponse;
import com.advocates.kdba.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BarAdminService {

    @Autowired
    private BarAdminRepository barAdminRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public GenericResponse getAdmin(){

        GenericResponse<String, String, List<BarAdmin>> generics= new GenericResponse<String, String, List<BarAdmin>>();

        List<BarAdmin> barAdmins =  barAdminRepository.findAll();
        generics.setObject(barAdmins);
        generics.setMessage("List of all KDBA Admins");

        return generics;
    }

    public GenericResponse getAdminByEmail(String email){
        BarAdmin barAdmin = barAdminRepository.findByEmail(email);
        GenericResponse<String, String, BarAdmin> generics= new GenericResponse<String, String, BarAdmin>();

        if (barAdmin == null){
            generics.setMessage("Admin Doesnt exist with mail: "+email);
            return generics;
        }
        else {
            generics.setObject(barAdmin);
            generics.setMessage("Admin Found with mail: "+email);
            return generics;
        }
    }

    public GenericResponse registerAdmin(BarAdmin barAdmin){

        BarAdmin findBarAdminByEmail = barAdminRepository.findByEmail(barAdmin.getEmail());
        GenericResponse<String, String, BarAdmin> generics= new GenericResponse<String, String, BarAdmin>();
        if(findBarAdminByEmail != null) {
            generics.setMessage("Admin Already Exists with Mail :"+findBarAdminByEmail.getEmail());

            return generics;
        }
        else {
            String Pass = barAdmin.getPassword();
            barAdmin.setPassword(encoder.encode(Pass));

            barAdminRepository.save(barAdmin);

            generics.setMessage("Admin Successfully Registered with Mail : "+barAdmin.getEmail());

            return generics;
        }

    }

    public GenericResponse getAdminByEmailAndPass(BarAdmin barAdmin){
        BarAdmin findBarAdminByEmail = barAdminRepository.findByEmail(barAdmin.getEmail());
        GenericResponse<String, String, BarAdmin> generics= new GenericResponse<String, String, BarAdmin>();

        if(findBarAdminByEmail == null) {
            generics.setMessage("Couldnt Find Admin with Mail :"+barAdmin.getEmail());
            generics.setToken(null);
            return generics;
        }

        else if(encoder.matches(barAdmin.getPassword(), findBarAdminByEmail.getPassword())){

            String token = jwtUtil.generateToken(barAdmin.getEmail());
            generics.setToken(token);
            generics.setObject(findBarAdminByEmail);
            generics.setMessage("Admin Credentials Matched with Mail "+barAdmin.getEmail()+" and Password ");

            return generics;
        }
        else {
            generics.setMessage("Admin Credentials Didn't Match");
            generics.setToken(null);
            return generics;
        }
    }

    public GenericResponse updateAdmin(BarAdmin existingAdmin,BarAdmin barAdmin){

        existingAdmin.setEmail(barAdmin.getEmail());
        barAdminRepository.save(existingAdmin);

        GenericResponse<String, String, BarAdmin> generics= new GenericResponse<String, String, BarAdmin>();
        generics.setMessage("Admin Successfully Updated");

        return generics;

    }

    public GenericResponse updateAdminPassword(BarAdmin existingAdmin,BarAdmin barAdmin){

        existingAdmin.setPassword(encoder.encode(barAdmin.getPassword()));
        barAdminRepository.save(existingAdmin);

        GenericResponse<String, String, BarAdmin> generics= new GenericResponse<String, String, BarAdmin>();
        generics.setMessage("Admin Password Successfully Updated");

        return generics;

    }

    public GenericResponse deleteAdmin(String id) {
        barAdminRepository.deleteById(id);

        GenericResponse<String, String, BarAdmin> generics= new GenericResponse<String, String, BarAdmin>();
        generics.setMessage("Admin Successfully Deleted");

        return generics;


    }
}
