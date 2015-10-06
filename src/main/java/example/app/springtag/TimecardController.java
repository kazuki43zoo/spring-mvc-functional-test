package example.app.springtag;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Date;

@RequestMapping("/timecards")
@Controller
public class TimecardController {

//	@RequestMapping(path = "{targetMonth}", method = RequestMethod.GET)
//	public String viewDetail(@DateTimeFormat(pattern = "yyyyMM") @PathVariable Date targetMonth, Model model) {
//		model.addAttribute("targetMonth", targetMonth);
//		return "user/detail";
//	}

//	@RequestMapping(path = "{targetMonth}", method = RequestMethod.GET)
//	public String viewDetail(@DateTimeFormat(pattern = "yyyyMM") @PathVariable YearMonth targetMonth, Model model) {
//		model.addAttribute("targetMonth", targetMonth);
//		return "user/detail";
//	}

//	@RequestMapping(path = "{targetMonth}", method = RequestMethod.GET)
//	public String viewDetail(@DateTimeFormat(pattern = "yyyyMM") @PathVariable LocalDate targetMonth, Model model) {
//		model.addAttribute("targetMonth", targetMonth);
//		return "user/detail";
//	}

	@RequestMapping(path = "{targetMonth}", method = RequestMethod.GET)
	public String viewDetail(
			@DateTimeFormat(pattern = "yyyyMM") @PathVariable Date targetMonth,
			Principal principal, Model model) {
		model.addAttribute("targetMonth", targetMonth);
		return "user/detail";
	}

}
