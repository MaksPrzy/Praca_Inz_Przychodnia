package pl.przybylo.przychodnia.domain.repository;

public interface Repository<ENTITY> {

    ENTITY findByIdOrThrowException(long id);

}
