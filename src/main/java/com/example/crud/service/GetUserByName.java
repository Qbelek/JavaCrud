package com.example.crud.service;

import an.awesome.pipelinr.Command;
import com.example.crud.dbaccess.User;
import com.example.crud.misc.Error;
import io.vavr.control.Either;

public class GetUserByName implements Command<Either<Error, User>> {

    private final String name;

    public GetUserByName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
