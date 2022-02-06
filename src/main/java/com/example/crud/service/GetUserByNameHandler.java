package com.example.crud.service;

import an.awesome.pipelinr.Command;
import com.example.crud.dbaccess.User;
import com.example.crud.dbaccess.UserRepository;
import com.example.crud.misc.Error;

import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

import static com.example.crud.dbaccess.Transactional.doInTransaction;


@Component
public class GetUserByNameHandler implements Command.Handler<GetUserByName, Either<Error, User>> {

    private final UserRepository userRepository;
    private final EntityManager em;

    @Autowired
    public GetUserByNameHandler(UserRepository userRepository, EntityManager em) {
        this.userRepository = userRepository;
        this.em = em;
    }

    @Override
    public Either<Error, User> handle(GetUserByName getUserByName) {

        var name = getUserByName.getName();
        //var userFuture = userRepository.findUserByName(name);

        return doInTransaction(em, () -> {
            var user = Try.of(() -> em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult());

            return user.isSuccess() ?
                    Either.right(user.get()) :
                    Either.left(new Error(user.getCause().getMessage()));
        });
    }
}
