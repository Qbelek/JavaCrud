package com.example.crud.service;

import an.awesome.pipelinr.Command;
import com.example.crud.dbaccess.User;
import com.example.crud.misc.Error;

import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class ChangeUserNameHandler implements Command.Handler<ChangeUserName, CompletableFuture<Either<Error, User>>> {

    @PersistenceContext
    private EntityManager em;

    private final ExecutorService executor;

    public ChangeUserNameHandler() {
        this.executor = Executors.newCachedThreadPool();
    }

    @Override
    @Transactional
    public CompletableFuture<Either<Error, User>> handle(ChangeUserName changeUserName) {

        var id = changeUserName.getId();
        var newName = changeUserName.getNewName();

        return getUser(id)
                .thenCompose(x -> changeUserName(x, newName))
                .thenApply(x -> x.isSuccess() ?
                        Either.right(x.get()) :
                        Either.left(new Error(x.getCause().getMessage()))
                );
    }

    private CompletableFuture<Try<User>> getUser(Long id) {

        return CompletableFuture.supplyAsync(() ->
                Try.of(() ->
                        em.find(User.class, id)),
                executor);
    }

    private CompletableFuture<Try<User>> changeUserName(Try<User> user, String newName) {

        return CompletableFuture.supplyAsync(() -> user.flatMap(x ->
                Try.of(() -> {
                    x.setName(newName);
                    em.merge(x);
                    return x;
                })
        ), executor);
    }
}
