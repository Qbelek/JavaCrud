package com.example.crud.dbaccess;

import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import org.springframework.data.repository.CrudRepository;

import java.util.concurrent.CompletableFuture;


public interface UserRepository extends CrudRepository<User, Long> {

    CompletableFuture<Option<User>> findUserByName(String name);
}
