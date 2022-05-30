package web_service.service.impl;
/*
    Created by Trinh Khai
    Date: 26/05/2022
    Time: 09:58
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web_service.model.Account;
import web_service.repository.IAccountRepository;
/*
* UserDetailsService là interface có phương thức loadUserByUsername để lấy thông tin người dùng
* bằng username và trả về 1 đối tượng UserDetails mà Spring Security có thể sử dụng để xác thực và xác nhận.
*/
@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        Account account = iAccountRepository.findByUserName(username);
        if (account == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        System.out.println("loadUserByUsername() at AccountDetailsServiceImpl in service package.");
        return AccountDetailsImpl.build(account);
    }
}
