package com.example.crud.service;

import an.awesome.pipelinr.Command;
import com.example.crud.dbaccess.User;
import com.example.crud.misc.Error;
import io.vavr.control.Either;

import java.util.concurrent.CompletableFuture;

public class ChangeUserName implements Command<CompletableFuture<Either<Error, User>>> {

    private final Long id;
    private final String newName;

    public ChangeUserName(Long id, String newName) {

        this.id = id;
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }

    public Long getId() {
        return id;
    }
}
