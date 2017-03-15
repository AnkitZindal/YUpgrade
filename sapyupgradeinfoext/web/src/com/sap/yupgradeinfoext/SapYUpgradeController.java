package com.sap.yupgradeinfoext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * The Class SapYUpgradeController.
 */
@Controller
@RequestMapping(value = "/upgrade")
public class SapYUpgradeController
{

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(SapYUpgradeController.class);

	/**
	 * Gets the document.
	 *
	 * @param request
	 *           the request
	 * @param model
	 *           the model
	 * @param response
	 *           the response
	 * @return the document
	 * @throws IOException
	 *            Signals that an I/O exception has occurred.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getDocument(final HttpServletRequest request, final Model model, final HttpServletResponse response)
			throws IOException
	{
		return "index.jsp";
	}

}
