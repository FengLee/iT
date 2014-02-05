package cz.cvut.iTracker.domain;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * 
 * @author vavat
 *
 */
@Entity
public class Project extends ITEntity{
	@Column(nullable = false)
    private String name;
	
    @ManyToMany(mappedBy="projects")
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        if(users == null){
            users = new ArrayList<User>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
