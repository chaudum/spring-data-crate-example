package io.crate.hellospring;

import org.springframework.data.crate.repository.CrateRepository;


public interface UserRepository extends CrateRepository<User, String> {

}
