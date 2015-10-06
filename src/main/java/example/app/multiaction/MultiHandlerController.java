package example.app.multiaction;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/multiaction/*")
public class MultiHandlerController extends MultiActionController {

	public String form(HttpServletRequest request, HttpServletResponse response) {
		return "multiaction/form";
	}

}
