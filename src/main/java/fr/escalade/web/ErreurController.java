package fr.escalade.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErreurController implements ErrorController  {
	
	private static final Logger logger = LoggerFactory.getLogger(ErreurController.class);

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if(statusCode == HttpStatus.FORBIDDEN.value()) {
				logger.warn("Erreur 403 accès non autorisé " + HttpStatus.FORBIDDEN);
				return "403";
			} else if(statusCode == HttpStatus.NOT_FOUND.value()) {
				logger.warn("Erreur 404 page non trouvée " + HttpStatus.NOT_FOUND);
				return "404";
			} else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				logger.warn("Erreur 500 problème côté serveur " + HttpStatus.INTERNAL_SERVER_ERROR);
				return "500";
			}
		}
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
