package com.accenture.findmyapp.controller;

import java.awt.HeadlessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.accenture.findmyapp.business.HomeDataHandler;
import com.accenture.findmyapp.entity.ErrorMessage;
import com.accenture.findmyapp.entity.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private HomeDataHandler data;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public String getPersonByName(@PathVariable("name") String name,
			ModelMap model) {

		logger.info("getPersonByName { " + name + " }");
		Person person = data.getPerson(name);

		model.addAttribute("person", person);
		return "home";
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.PUT)
	public void insertPerson(@PathVariable("name") String name,
			@RequestParam("height") String height, ModelMap model) {
		int status = 0;

		if (height != null && height.length() > 0) {
			logger.info("insertPersonWithNameAndHeight { " + name + ", "
					+ height + " }");
			int heightInt;
			try {
				heightInt = Integer.parseInt(height);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(e);
			}
			status = data.insertPerson(name, heightInt);
		} else {
			logger.info("insertPersonWithName { " + name + " }");
			status = data.insertPerson(name);
		}
		
		model.addAttribute("status", status);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleEmptyResultDataAccessException(
			EmptyResultDataAccessException ex) {
		logger.info("handleEmptyResultDataAccessException ( "
				+ ex.getLocalizedMessage() + " )");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Illegal argument")
	public void handleIllegalArgumentException(IllegalArgumentException ex) {
		logger.info("handleIllegalArgumentException ( "
				+ ex.getLocalizedMessage() + " )");
	}

}
