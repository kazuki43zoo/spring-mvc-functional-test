package example.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequestMapping("requestparam")
@Controller
public class RequestParamController {

    @RequestMapping(path = "string", method = RequestMethod.GET)
    public String string(@RequestParam String word, Model model) {
        model.addAttribute("word", word);
        return "requestparam/string";
    }

    @RequestMapping(path = "stringWithName", method = RequestMethod.GET)
    public String stringWithName(@RequestParam("word") String string, Model model) {
        model.addAttribute("word", string);
        return "requestparam/stringWithName";
    }

    @RequestMapping(path = "stringWithRequired", method = RequestMethod.GET)
    public String stringWithRequired(@RequestParam(required = false) String string, Model model) {
        if (string == null) {
            string = "word is null";
        }
        model.addAttribute("word", string);
        return "requestparam/stringWithRequired";
    }

    @RequestMapping(path = "stringWithDefaultValue", method = RequestMethod.GET)
    public String stringWithDefaultValue(@RequestParam(defaultValue = "*") String string, Model model) {
        model.addAttribute("word", string);
        return "requestparam/stringWithDefaultValue";
    }

    @RequestMapping(path = "stringWithRequiredAndDefaultValue", method = RequestMethod.GET)
    public String stringWithRequiredAndDefaultValue(@RequestParam(required = true, defaultValue = "*") String string, Model model) {
        model.addAttribute("word", string);
        return "requestparam/stringWithRequiredAndDefaultValue";
    }

    @RequestMapping(path = "integer", method = RequestMethod.GET)
    public String integer(@RequestParam Integer id, Model model) {
        model.addAttribute("id", id);
        return "requestparam/integer";
    }

}
