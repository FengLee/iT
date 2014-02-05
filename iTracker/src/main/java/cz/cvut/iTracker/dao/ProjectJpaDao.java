package cz.cvut.iTracker.dao;

import org.springframework.stereotype.Repository;

import cz.cvut.iTracker.domain.Project;
/**
 * 
 * @author vavat
 *
 */
@Repository
public class ProjectJpaDao extends ITJpaBaseDao<Project> implements ProjectDao {

    public ProjectJpaDao() {
        super(Project.class);
    }
}