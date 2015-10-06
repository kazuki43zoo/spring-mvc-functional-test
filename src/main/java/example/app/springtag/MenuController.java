package example.app.springtag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/menu")
@Controller
public class MenuController {

	@RequestMapping(method = RequestMethod.GET)
	public String view() {
		return "welcome/menu";
	}

}
