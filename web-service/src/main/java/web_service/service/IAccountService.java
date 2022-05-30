package web_service.service;
/*
    Created by Trinh Khai
    Date: 26/05/2022
    Time: 09:34
*/

import web_service.model.Account;

public interface IAccountService extends IGeneralService<Account>{
    Account findByUserName(String username);

    String setVetificationCode(Account account);
}
