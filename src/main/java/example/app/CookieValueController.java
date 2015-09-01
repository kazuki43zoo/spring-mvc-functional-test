package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RequestMapping("cookie-value")
@Controller
public class CookieValueController {

    @RequestMapping(path = "string", method = RequestMethod.GET)
    public void string(@CookieValue String word, Model model) {
        model.addAttribute("word", word);
    }

    @RequestMapping(path = "stringWithName", method = RequestMethod.GET)
    public void stringWithName(@CookieValue("word") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "stringWithRequired", method = RequestMethod.GET)
    public void stringWithRequired(@CookieValue(required = false) String string, Model model) {
        if (string == null) {
            string = "word is null";
        }
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "stringWithOptional", method = RequestMethod.GET)
    public void stringWithOptional(@CookieValue Optional<String> word, Model model) {
        model.addAttribute("word", word.orElse("word is null"));
    }

    @RequestMapping(path = "stringWithOptionalAndDefaultValue", method = RequestMethod.GET)
    public void stringWithOptionalAndDefaultValue(@CookieValue(defaultValue = "*") Optional<String> word, Model model) {
        model.addAttribute("word", word.get());
    }

    @RequestMapping(path = "stringWithDefaultValue", method = RequestMethod.GET)
    public void stringWithDefaultValue(@CookieValue(defaultValue = "*") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "stringWithRequiredAndDefaultValue", method = RequestMethod.GET)
    public void stringWithRequiredAndDefaultValue(@CookieValue(required = true, defaultValue = "*") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "integer", method = RequestMethod.GET)
    public void integer(@CookieValue Integer id, Model model) {
        model.addAttribute("id", id);
    }

    @RequestMapping(path = "allCookies", method = RequestMethod.GET)
    public void allParams(@CookieValue Map<String,String> cookies, Model model) {
        model.addAttribute("cookies", String.valueOf(cookies));
    }
}
