package cz.cvut.iTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.cvut.iTracker.dao.ProjectDao;
import cz.cvut.iTracker.domain.Project;
import cz.cvut.iTracker.domain.User;
import cz.cvut.iTracker.web.form.ProjectForm;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private UserService userService;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addProject(ProjectForm form, Long id) {
		User user = userService.findById(id);
		Project p = new Project();
		p.setName(form.getName());
		projectDao.persist(p);
		user.getProjects().add(p);
		userService.update(user);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProject(ProjectForm form, Long id) {
		Project p = projectDao.findById(id);
		if(p == null){
			return;
		}
		p.setName(form.getName());
		projectDao.update(p);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeProject(Long projId) {
		Project p = findById(projId);
		List<User> res = p.getUsers();		
		for(int i = 0; i < res.size(); i++){
			res.get(i).removeProject(projId);
			userService.update(res.get(i));
		}
		projectDao.remove(p);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Project findById(Long id) {
		Project p = projectDao.findById(id);
		if(p != null){
			p.getUsers().size();
		}
		return p;
	}

}
