package cz.cvut.iTracker.service;

import java.util.List;

import cz.cvut.iTracker.domain.User;
import cz.cvut.iTracker.web.form.UserForm;
/**
 * 
 * @author vavat
 *
 */
public interface UserService {
	/**
	 * Method for getting list of all users.
	 * 
	 */
	public List<User> getAll();
	/**
	 * Method for updating {@link User}.
	 * 
	 * @param user {@link User} to update.
	 */
	public void update(User user);
	/**
	 * Method for obtaining {@link User} by id.
	 * 
	 * @param userId Id of user to find.
	 * @return {@link User} found in database.
	 */
	public User findById(Long userId);
	/**
	 * Method for adding new user
	 * 
	 * @param form {@link UserForm} with data of new user
	 * @return Return new user's id;
	 */
	public Long add(UserForm form);
}
