package com.advocates.kdba.repository;

import com.advocates.kdba.models.BarAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BarAdminRepository extends MongoRepository<BarAdmin,String> {

    BarAdmin findByEmail(String email);
}
