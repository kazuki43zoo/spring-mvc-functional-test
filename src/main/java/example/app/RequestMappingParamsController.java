package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "request-mapping", params = "!criteria1")
@Controller
public class RequestMappingParamsController {

    @RequestMapping(path = "param", params = "criteria2")
    public void param() {
    }

    @RequestMapping(path = "param", params = {"criteria3", "criteria4"})
    public String paramMulti() {
        return "request-mapping/paramMulti";
    }

    @RequestMapping(path = "param", params = {"criteria5", "criteria5=1"})
    public String paramValueIsEqual() {
        return "request-mapping/paramValueIsEqual";
    }

    @RequestMapping(path = "param", params = {"criteria5", "criteria5!=1"})
    public String paramValueIsNotEqual() {
        return "request-mapping/paramValueIsNotEqual";
    }

}
