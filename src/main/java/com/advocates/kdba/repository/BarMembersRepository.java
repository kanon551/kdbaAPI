package com.advocates.kdba.repository;

import com.advocates.kdba.models.BarMembers;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarMembersRepository extends MongoRepository<BarMembers,String> {

    public BarMembers findByEnrollmentNo(String enrollmentNo);
}
