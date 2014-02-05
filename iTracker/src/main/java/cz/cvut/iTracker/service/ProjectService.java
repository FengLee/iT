package cz.cvut.iTracker.service;

import cz.cvut.iTracker.domain.Project;
import cz.cvut.iTracker.web.form.ProjectForm;
public interface ProjectService {
	/**
	 * Method for adding new project.
	 * 
	 * @param form {@link ProjectForm} with data about new project
	 * @return Id of new project
	 */
	public void addProject(ProjectForm form, Long id);
	/**
	 * Updates {@link Project} entity by given id.
	 * 
	 * @param form {@link ProjectForm} with updated data of project
	 * @param id Id of entity to update
	 */
	public void updateProject(ProjectForm form, Long id);
	/**
	 * Method for remove project by given id.
	 * 
	 * @param id Id of entity to remove
	 */
	public void removeProject(Long projId);
	/**
	 * Method for obtaining {@link Project} by id.
	 * 
	 * @param id Id of project to find.
	 * @return {@link Project} found in database.
	 */	
	public Project findById(Long id);
}
