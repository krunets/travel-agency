package by.runets.travelagency.hibernate;

import java.util.Optional;

public interface IUserRepository<T, K> {
	Optional<T> findUserByLogin(K login);
}