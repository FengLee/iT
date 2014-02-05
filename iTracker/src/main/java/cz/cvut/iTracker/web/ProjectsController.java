package cz.cvut.iTracker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cz.cvut.iTracker.domain.Project;
import cz.cvut.iTracker.domain.User;
import cz.cvut.iTracker.service.ProjectService;
import cz.cvut.iTracker.service.UserService;
import cz.cvut.iTracker.web.form.ProjectForm;

@Controller
public class ProjectsController {
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{userId}/projects", method = RequestMethod.GET)
	public String projects(@PathVariable Long userId, ModelMap model){		
		User user = userService.findById(userId);
		List<Project> res = user.getProjects();
		model.put("projects", res);
		model.put("test", user.isBlockCreate());
		return "projects";
	}
	
	@RequestMapping(value="/{userId}/projects/add", method=RequestMethod.GET)
	public ModelAndView showAddProject(@PathVariable Long userId, ModelMap model){				
		return new ModelAndView("addProject", "command", new ProjectForm());
	}
	
	@RequestMapping(value="/{userId}/projects/add", method = RequestMethod.POST)
	public String addProject(@PathVariable Long userId, @ModelAttribute("SpringWeb") ProjectForm form) {
		projectService.addProject(form, userId);
		return "redirect:/{userId}/projects";
	}
	
	@RequestMapping(value="/{userId}/projects/{projId}/edit", method=RequestMethod.GET)
	public ModelAndView showEditProject(@PathVariable Long projId, ModelMap model){
		ModelAndView mav = new ModelAndView("editProject", "command", new ProjectForm()); 
		Project p = projectService.findById(projId);
		mav.addObject("project", p);
		return mav;
	}
	
	@RequestMapping(value="/{userId}/projects/{projId}/edit", method=RequestMethod.POST)
	public String editProject(@PathVariable Long projId, @ModelAttribute("SpringWeb") ProjectForm form){
		projectService.updateProject(form, projId);
		return "redirect:/{userId}/projects";
	}
	@RequestMapping(value="/{userId}/projects/{projId}/delete", method=RequestMethod.GET)
	public String deleteProject(@PathVariable Long userId, @PathVariable Long projId){
		projectService.removeProject(projId);
		return "redirect:/{userId}/projects";
	}
}
