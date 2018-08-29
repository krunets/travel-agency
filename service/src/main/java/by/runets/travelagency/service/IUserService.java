package by.runets.travelagency.service;

public interface IUserService<T,K> extends IService<T, K> {
	boolean registerUserAccount(T user);
}
