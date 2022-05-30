package web_service.service;
/*
    Created by Trinh Khai
    Date: 17/04/2022
    Time: 15:18
*/


import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Integer id);

    T save(T t);
}
