package com.example.crud.web;

import an.awesome.pipelinr.Pipeline;
import com.example.crud.dbaccess.User;
import com.example.crud.misc.Error;
import com.example.crud.service.ChangeUserName;
import io.vavr.control.Either;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class CrudController {

    private final Pipeline pipeline;
    private final ApplicationContext context;

    public CrudController(Pipeline pipeline, ApplicationContext context) {
        this.pipeline = pipeline;
        this.context = context;
    }

    @GetMapping("/")
    public CompletableFuture<Either<Error, User>> hello() {

        return new ChangeUserName(1L, "name").execute(pipeline);
    }
}
