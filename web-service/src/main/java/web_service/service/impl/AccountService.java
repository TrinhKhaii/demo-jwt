package web_service.service.impl;
/*
    Created by Trinh Khai
    Date: 26/05/2022
    Time: 09:35
*/

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web_service.model.Account;
import web_service.repository.IAccountRepository;
import web_service.service.IAccountService;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public Iterable<Account> findAll() {
        return null;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Account save(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public Account findByUserName(String username) {
        return iAccountRepository.findByUserName(username);
    }

    @Override
    public String setVetificationCode(Account account) {
        String code = RandomString.make(6);
        iAccountRepository.setVetification(account.getUserName(), code);
        return code;
    }
}
