package cz.cvut.iTracker.dao;

import org.springframework.stereotype.Repository;

import cz.cvut.iTracker.domain.User;
/**
 * 
 * @author vavat
 *
 */
@Repository
public class UserJpaDao extends ITJpaBaseDao<User> implements UserDao {
    public UserJpaDao() {
        super(User.class);
    }
}
