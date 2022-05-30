package web_service.repository;
/*
    Created by Trinh Khai
    Date: 26/05/2022
    Time: 09:31
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web_service.model.Account;

import javax.transaction.Transactional;

@Transactional
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    Account findByUserName(String username);

    @Modifying
    @Query(value = "UPDATE account SET verification_code = :code WHERE (user_name = :username); ", nativeQuery = true)
    void setVetification(@Param("username") String username, @Param("code") String code);
}
