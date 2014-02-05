package cz.cvut.iTracker.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cz.cvut.iTracker.domain.User;
import cz.cvut.iTracker.service.UserService;
import cz.cvut.iTracker.web.form.UserForm;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView showAddUser(){
		return new ModelAndView("addUser", "command", new UserForm());
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("SpringWeb") UserForm form){
		userService.add(form);
		return "redirect:/users";
	}
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String showUsers(ModelMap model){
		List<User> res = userService.getAll();
		model.put("users", res);
		return "users";
	}
	
	@RequestMapping(value="/blockLog", method=RequestMethod.GET)
	public String blockLogin(HttpServletRequest request, @RequestParam Long id, @RequestParam Long idl){
		User u = userService.findById(id);
		u.setBlockLogin();
		userService.update(u);		
		return "redirect:/users";
	}
	
	@RequestMapping(value="/blockCreate", method=RequestMethod.GET)
	public String blockCreate(@RequestParam Long id){
		User u = userService.findById(id);
		u.setBlockCreate();
		userService.update(u);
		return "redirect:/users";
	}
}
