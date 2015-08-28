package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "request-mapping/header", headers = "!criteria1")
@Controller
public class RequestMappingHeadersController {

    @RequestMapping(headers = "criteria2")
    public void header() {
    }

    @RequestMapping(headers = {"criteria3", "criteria4"})
    public String headerMulti() {
        return "request-mapping/headerMulti";
    }

    @RequestMapping(headers = {"criteria5", "criteria5=1"})
    public String headerValueIsEqual() {
        return "request-mapping/headerValueIsEqual";
    }

    @RequestMapping(headers = {"criteria5", "criteria5!=1"})
    public String headerValueIsNotEqual() {
        return "request-mapping/headerValueIsNotEqual";
    }

}
