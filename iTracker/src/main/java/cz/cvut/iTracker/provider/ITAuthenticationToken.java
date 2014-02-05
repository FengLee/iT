package cz.cvut.iTracker.provider;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ITAuthenticationToken extends AbstractAuthenticationToken{
	
	private static final long serialVersionUID = -6189249471411651857L;
	
	/**
	 * This field usually contains username.
	 */
	private final Object principal;
	
	/**
	 * This field usually contains password.
	 */
    private Object credentials;
    
    /**
     * This field contains custom information about user. In this case it contains id of logged user.
     */
    private Object details;
    
    /**
     * Constructor setting username and password.
     * 
     * @param principal {@link Object} which contains username.
     * @param credentials {@link Object} which contains password.
     */
    public ITAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }
    
    /**
     * Constructor setting username, password, granted authorities and details.
     * 
     * @param principal {@link Object} which contains username.
     * @param credentials {@link Object} which contains password.
     * @param authorities {@link Collection} of granted authorities.
     * @param details {@link Object} which contains id of logged user.
     */
    public ITAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Object details) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.details = details;
        super.setAuthenticated(true);
    }

    /**
     * Getter of password.
     */
	@Override
	public Object getCredentials() {
		return credentials;
	}

	/**
	 * Getter of username.
	 */
	@Override
	public Object getPrincipal() {
		return principal;
	}
	
	/**
	 * Setter of boolean indicating whether user is authenticated.
	 */
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

	/**
	 * Erases stored password.
	 */
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

    /**
     * Getter of logged user's id.
     */
	@Override
	public Object getDetails() {
		return details;
	}
	
}
