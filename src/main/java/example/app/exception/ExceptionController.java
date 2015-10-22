package example.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class ResourceNotFoundException extends RuntimeException {
	}

	public static class MemberNotFoundException extends ResourceNotFoundException {
	}


}
