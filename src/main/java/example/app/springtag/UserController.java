package example.app.springtag;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/users")
@Controller
public class UserController {

	@RequestMapping(path = "{userId}", method = RequestMethod.GET)
	public String viewDetailByPathVariable(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId);
		return "user/detail";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String viewDetailByRequestParam(@RequestParam String userId, Model model) {
		model.addAttribute("userId", userId);
		return "user/detail";
	}

}
