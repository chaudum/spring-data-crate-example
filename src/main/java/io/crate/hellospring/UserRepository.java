package io.crate.hellospring;

import org.springframework.data.crate.repository.CrateRepository;
import org.springframework.data.crate.repository.support.CrateEntityInformation;

public interface UserRepository extends CrateRepository<User, String> {

//    public CrateEntityInformation<User, String> getEntityInformation();

}
