package com.example.crud.dbaccess;

import io.vavr.Function0;
import io.vavr.control.Either;
import com.example.crud.misc.Error;

import javax.persistence.EntityManager;

public final class Transactional {

    private Transactional() { }

    public static <T> Either<Error, T> doInTransaction(EntityManager em, Function0<Either<Error, T>> func) {

        em.getTransaction().begin();

        Either<Error, T> result = func.apply();

        return result.bimap(
                error -> {
                    em.getTransaction().rollback();
                    return error;
                },
                val -> {
                    em.getTransaction().commit();
                    return val;
                }
        );
    }
}
