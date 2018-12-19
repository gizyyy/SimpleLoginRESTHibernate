package com.task.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.constants.HelperConstants;

@RestController
@RequestMapping("/rest")
public class RestAgeController {
	/**
	 * Controls the age if below lower limit or upper limit, also checks prime.
	 * 
	 * @param ageNumber
	 * @return
	 */
	@RequestMapping(value = "/age/{ageNumber}", method = RequestMethod.GET)
	public String getAge(@PathVariable final String ageNumber) {

		String message = new String();
		int age = Integer.parseInt(ageNumber);
		if (age < HelperConstants.LOWER_LIMIT) {
			message = HelperConstants.TOO_YOUNG;
		} else if (age > HelperConstants.UPPER_LIMIT) {
			message = HelperConstants.TOO_OLD;
		} else {
			// check prime
			message = HelperConstants.FUNNY;
			for (int i = 2; i < age; i++) {
				if (age % i == 0) {
					message = HelperConstants.OK;
				}

			}
		}
		return message;
	}

}
