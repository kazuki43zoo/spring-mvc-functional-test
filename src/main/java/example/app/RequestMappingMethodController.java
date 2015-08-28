package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "request-mapping", method = RequestMethod.GET)
@Controller
public class RequestMappingMethodController {

    @RequestMapping(path = "get", method = RequestMethod.GET)
    public void get() {
    }

    @RequestMapping(path = "method", method = RequestMethod.HEAD)
    public String head() {
        return "request-mapping/head";
    }

    @RequestMapping(path = "method", method = RequestMethod.POST)
    public String post() {
        return "request-mapping/post";
    }

    @RequestMapping(path = "method", method = RequestMethod.DELETE)
    public String delete() {
        return "request-mapping/delete";
    }

    @RequestMapping(path = "method", method = RequestMethod.PUT)
    public String put() {
        return "request-mapping/put";
    }

    @RequestMapping(path = "method", method = RequestMethod.PATCH)
    public String patch() {
        return "request-mapping/patch";
    }

    @RequestMapping(path = "method", method = RequestMethod.OPTIONS)
    public String options() {
        return "request-mapping/options";
    }

    @RequestMapping(path = "method", method = RequestMethod.TRACE)
    public void trace() {
    }

}
