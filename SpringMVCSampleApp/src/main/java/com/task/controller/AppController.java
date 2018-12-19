package com.task.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.task.constants.HelperConstants;
import com.task.model.LoginUser;
import com.task.service.LoginUserService;

/**
 * Controller for User Operations
 * 
 * @author gizem
 *
 */
@Controller
@RequestMapping("/")
@ComponentScan("com.task")
public class AppController {

	@Autowired
	LoginUserService service;

	@Autowired
	MessageSource messageSource;

	/**
	 * Shows login form.
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("loginUser", new LoginUser());
		mav.addObject("login", true);
		mav.addObject("ageText", "");

		return mav;
	}

	/**
	 * Adds user to database.
	 * 
	 * @param loginUser
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public ModelAndView saveUser(@Valid LoginUser loginUser, BindingResult result, ModelMap model) {
		ModelAndView mav = new ModelAndView("index");
		// if name or surname is empty or cant pass length validation
		if (result.hasErrors()) {

			mav.addObject("login", true);
			mav.addObject("ageText", "");
			return mav;

		}
		// age check
		if (loginUser.getAge() < HelperConstants.LOWER_LIMIT || loginUser.getAge() > HelperConstants.UPPER_LIMIT) {
			mav.addObject("ageText", HelperConstants.AGE_INVALID);
			mav.addObject("login", true);
			return mav;
		}

		if (!result.hasErrors()) {

			service.saveUser(loginUser);
			mav.addObject("ageText", "");
			mav.addObject("login", false);
			mav.addObject("success", "User " + loginUser.getName() + " registered successfully");
		}

		return mav;
	}

	/**
	 * Gets users from database and shows
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/show/users" }, method = RequestMethod.GET)
	public ModelAndView showUsers() {
		ModelAndView mav = new ModelAndView("users");
		List<LoginUser> findAllUsers = service.findAllUsers();
		mav.addObject("users", findAllUsers);
		return mav;

	}

	/**
	 * Deletes selected user from database.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@PathVariable String id) {
		service.deleteUser(id);
		ModelAndView mav = new ModelAndView("users");
		List<LoginUser> findAllUsers = service.findAllUsers();
		mav.addObject("users", findAllUsers);
		return mav;
	}

}
