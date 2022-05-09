package com.Ashesi.ASHRC.RepositoriesDAO;

import com.Ashesi.ASHRC.Model.UserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface UserRepository extends CrudRepository<UserDetails,Long> {

    @Override
    List<UserDetails> findAll();

    @Query("SELECT c FROM UserDetails c WHERE c.email = ?1")
    public UserDetails findByEmail(String email);

    public UserDetails findByResetPasswordToken(String token);

}
