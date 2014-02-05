package cz.cvut.iTracker.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import cz.cvut.iTracker.provider.HashProvider;
import cz.cvut.iTracker.provider.SHA1Provider;

/**
 * 
 * @author vavat
 *
 */
@Entity
@Table(name="Users")
@NamedQueries ({
	@NamedQuery(name = User.FIND_BY_LOGIN, query = "SELECT u FROM User u WHERE u.login = (:login)")
})
public class User extends ITEntity{
	
	public static final String FIND_BY_LOGIN = "User.findByLogin";
	@Column
	private String name;
	@Column
	private String surname;
	@Column(unique = true,nullable = false)
    private String login;
	@Column(length = 40, nullable = false)
    private String pass;
	@Column(length = 40, nullable = false)
    private String salt;
	@Column
	private Roles auth;
	@Column(nullable = false)
	private boolean blockCreate;
	@Column(nullable = false)
	private boolean blockLogin;

	@ManyToMany
    private List<Project> projects;
    
    @Autowired
	private transient HashProvider hashProvider;
          
    public boolean isBlockCreate() {
		return blockCreate;
	}

	public void setBlockCreate(boolean blockCreate) {
		this.blockCreate = blockCreate;
	}
	
	public void setBlockCreate() {
		if(this.blockCreate == false){
			this.blockCreate = true;
		}else{
			this.blockCreate = false;
		}
	}
	
	public boolean isBlockLogin() {
		return blockLogin;
	}

	public void setBlockLogin(boolean blockLogin) {
		this.blockLogin = blockLogin;
	}
	
	public void setBlockLogin() {
		if(this.blockLogin == false){
			this.blockLogin = true;
		}else{
			this.blockLogin = false;
		}
	}
	
	public Roles getAuth() {
		return auth;
	}

	public void setAuth(Roles auth) {
		this.auth = auth;
	}
		
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
    	this.salt = getHashProvider().computeHash(System.nanoTime() + "");
        this.pass = getHashProvider().computeHash(pass + salt);
    }

    public List<Project> getProjects() {
        if(projects == null){
            projects = new ArrayList<Project>();
        }
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public HashProvider getHashProvider() {
		if(this.hashProvider == null){
			this.setHashProvider(new SHA1Provider());
		}
		return hashProvider;
	}

	public void setHashProvider(HashProvider hashProvider) {
		this.hashProvider = hashProvider;
	}
	/**
	 * Compare password from input with password in table 
	 * @param password string
	 * @return true if password is correct, false if incorrect
	 */
	public boolean hasPassword(String password){
        if(getHashProvider().computeHash(password + salt).equals(this.pass)){
            return true;
        }
        return false;
    }
	/**
	 * Remove project from user
	 * @param id Id of project to remove
	 */
	public void removeProject(Long id){
		List<Project> p = getProjects();
		for(int i = 0; i < p.size(); i++){
			if(p.get(i).getEntityId() == id){
				p.remove(i);
				break;
			}
		}
	}
}
