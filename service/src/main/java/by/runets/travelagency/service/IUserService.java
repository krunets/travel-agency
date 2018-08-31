package by.runets.travelagency.service;

import java.util.List;

public interface IUserService<T, K> extends IService<T, K> {
	boolean registerUserAccount (T user);
	
	List<T> readUserByRole ();
}
