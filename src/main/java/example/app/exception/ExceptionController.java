package example.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("exception")
public class ExceptionController {

	@RequestMapping("status")
	public void status() {
		throw new ResourceNotFoundException();

	}

	@RequestMapping("status/cause")
	public void statusCause() {
		throw new RuntimeException(new ResourceNotFoundException());

	}

	@RequestMapping("status/extends")
	public void statusExtends() {
		throw new MemberNotFoundException();
	}

	@RequestMapping("throwException")
	public void throwException() throws Exception {
		throw new Exception("test message");
	}
	@RequestMapping("throwError")
	public void throwError() throws Exception {
		throw new StackOverflowError("test message");
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "error/systemError";
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView("error/systemError");
		mav.getModelMap().addAttribute("message", e.getMessage());
		return mav;
	}


	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class ResourceNotFoundException extends RuntimeException {

	}

	public static class MemberNotFoundException extends ResourceNotFoundException {
	}


}
