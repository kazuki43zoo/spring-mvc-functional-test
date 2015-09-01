package example.app;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@RequestMapping("request-header")
@Controller
public class RequestHeaderController {

    @RequestMapping(path = "string", method = RequestMethod.GET)
    public void string(@RequestHeader String word, Model model) {
        model.addAttribute("word", word);
    }

    @RequestMapping(path = "stringWithName", method = RequestMethod.GET)
    public void stringWithName(@RequestHeader("word") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "stringWithRequired", method = RequestMethod.GET)
    public void stringWithRequired(@RequestHeader(required = false) String string, Model model) {
        if (string == null) {
            string = "word is null";
        }
        model.addAttribute("word", string);
    }
    @RequestMapping(path = "stringWithOptional", method = RequestMethod.GET)
    public void stringWithOptional(@RequestHeader Optional<String> word, Model model) {
        model.addAttribute("word", word.orElse("word is null"));
    }

    @RequestMapping(path = "stringWithOptionalAndDefaultValue", method = RequestMethod.GET)
    public void stringWithOptionalAndDefaultValue(@RequestHeader(defaultValue = "*") Optional<String> word, Model model) {
        model.addAttribute("word", word.get());
    }

    @RequestMapping(path = "stringWithDefaultValue", method = RequestMethod.GET)
    public void stringWithDefaultValue(@RequestHeader(defaultValue = "*") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "stringWithRequiredAndDefaultValue", method = RequestMethod.GET)
    public void stringWithRequiredAndDefaultValue(@RequestHeader(required = true, defaultValue = "*") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "integer", method = RequestMethod.GET)
    public void integer(@RequestHeader Integer id, Model model) {
        model.addAttribute("id", id);
    }

    @RequestMapping(path = "allHeaders", method = RequestMethod.GET)
    public void allParams(@RequestHeader HttpHeaders headers, Model model) {
        model.addAttribute("headers", String.valueOf(headers));
    }

}
