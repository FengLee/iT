package cz.cvut.iTracker.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import cz.cvut.iTracker.dao.UserDao;
import cz.cvut.iTracker.domain.User;

@Component("ITAuthProvider")
public class ITAuthenticationProvider implements AuthenticationProvider {
	
	/**
	 * {@link UserDao} for obtaining data about users.
	 */
	@Autowired
	private UserDao userDao;

	/**
	 * Custom implementation of authentication method.
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String login = token.getName();
        String password = token.getCredentials().toString();
        
        Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);
	    List<User> result = userDao.find(User.FIND_BY_LOGIN, parameters);
	    if (result.isEmpty()) {
	    	return null;
	    }
	    User persistedUser = result.get(0);
	    boolean correctPassword = persistedUser.hasPassword(password);
	    if (correctPassword && !persistedUser.isBlockLogin()) {
	    	List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	    	grantedAuths.add(new SimpleGrantedAuthority(persistedUser.getAuth().toString()));	    	
	    	Authentication auth = new ITAuthenticationToken(login, token.getCredentials(), grantedAuths, persistedUser.getEntityId());            
	    	return auth;
	    }
		return null;
	}

	/**
	 * Method for checking which class of token provider requires.
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
