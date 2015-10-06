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
		throw new FileNotFoundException();

	}

	@RequestMapping("status/cause")
	public void statusCause() {
		throw new RuntimeException(new FileNotFoundException());

	}

	@RequestMapping("status/extends")
	public void statusExtends() {
		throw new RuntimeException(new ResourceNotFoundException());

	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "test")
	public static class FileNotFoundException extends RuntimeException {

	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "test")
	public static class ResourceNotFoundException extends FileNotFoundException {

	}

}
