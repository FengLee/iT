package cz.cvut.iTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.cvut.iTracker.dao.UserDao;
import cz.cvut.iTracker.domain.Roles;
import cz.cvut.iTracker.domain.User;
import cz.cvut.iTracker.web.form.UserForm;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findById(Long userId) {
		User user = userDao.findById(userId);
		if (user != null) {
			user.getProjects().size();
			return user;
		} else {
			return null;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long add(UserForm form) {
		User u = new User();
		u.setName(form.getName());
		u.setSurname(form.getSurname());
		u.setLogin(form.getLogin());
		u.setPass(form.getPass());
		u.setBlockCreate(false);
		u.setBlockLogin(false);
		if(form.isAdmin()){
			u.setAuth(Roles.ADMIN);
		}else{
			u.setAuth(Roles.OWNER);
		}
		return userDao.persist(u);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> getAll() {		
		return userDao.loadAll();
	}

}
