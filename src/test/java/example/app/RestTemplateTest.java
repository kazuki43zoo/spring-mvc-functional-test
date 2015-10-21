package example.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestTemplateTest.AppConfig.class)
public class RestTemplateTest {

    @Autowired
    RestOperations restOperations;


    @Test
    public void testGet() {
        {
            String url = "http://www.mocky.io/v2/5185415ba171ea3a00704eed";
            String resource = restOperations.getForObject(url, String.class);
            System.out.println(resource);
        }
        {
            String url = "http://www.mocky.io/v2/5185415ba171ea3a00704eed";
            Map<String, Object> resource = restOperations.getForObject(url, Map.class);
            System.out.println(resource);
            System.out.println(resource.get("hello"));
        }
        {
            String url = "http://www.mocky.io/v2/5185415ba171ea3a00704eed";
            Greeting resource = restOperations.getForObject(url, Greeting.class);
            System.out.println(resource);
            System.out.println(resource.getHello());
        }

    }

    @Test
    public void testPost() {
        {
            String url = "http://www.mocky.io/v2/5627fa932700007e316eecb8";
            Greeting resource = new Greeting();
            resource.setHello("Japan");
            URI resourceUri = restOperations.postForLocation(url, resource);
            System.out.println(resourceUri);
        }

    }

    @Configuration
    public static class AppConfig {

        @Bean
        RestTemplate restTemplate() {
            ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                    .propertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                    .build();
            RestTemplate restTemplate = new RestTemplate(Arrays.asList(
                    new StringHttpMessageConverter(),
                    new MappingJackson2HttpMessageConverter(objectMapper)));
            restTemplate.setInterceptors(Arrays.asList(new RequestDumpInterceptor()));
            return restTemplate;
        }

    }

    public static class Greeting {
        private String hello;

        public String getHello() {
            return hello;
        }

        public void setHello(String hello) {
            this.hello = hello;
        }
    }


    public static class RequestDumpInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                ClientHttpRequestExecution execution) throws IOException {
            System.out.println("METHOD  : " + request.getMethod());
            System.out.println("URI     : " + request.getURI());
            System.out.println("HEADERS : " + request.getHeaders());
            System.out.println("BODY    : " + new String(body, StandardCharsets.UTF_8));
            return execution.execute(request, body);
        }
    }


    public static class User {

        private String login;
        private Integer id;
        private String avatarUrl;
        private String gravatarId;
        private String url;
        private String htmlUrl;
        private String followersUrl;
        private String followingUrl;
        private String gistsUrl;
        private String starredUrl;
        private String subscriptionsUrl;
        private String organizationsUrl;
        private String reposUrl;
        private String eventsUrl;
        private String receivedEventsUrl;
        private String type;
        private Boolean siteAdmin;
        private String name;
        private Object company;
        private String blog;
        private Object location;
        private Object email;
        private Object hireable;
        private Object bio;
        private Integer publicRepos;
        private Integer publicGists;
        private Integer followers;
        private Integer following;
        private Date createdAt;
        private ZonedDateTime updatedAt;


        public String getLogin() {
            return login;
        }


        public void setLogin(String login) {
            this.login = login;
        }


        public Integer getId() {
            return id;
        }


        public void setId(Integer id) {
            this.id = id;
        }


        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }


        public String getGravatarId() {
            return gravatarId;
        }


        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }


        public String getUrl() {
            return url;
        }


        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }


        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }


        public String getFollowersUrl() {
            return followersUrl;
        }


        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }


        public String getFollowingUrl() {
            return followingUrl;
        }


        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        public String getGistsUrl() {
            return gistsUrl;
        }

        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }


        public String getStarredUrl() {
            return starredUrl;
        }


        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }


        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }


        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }


        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }


        public String getReposUrl() {
            return reposUrl;
        }


        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }


        public String getEventsUrl() {
            return eventsUrl;
        }

        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }


        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }


        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public String getBlog() {
            return blog;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getHireable() {
            return hireable;
        }

        public void setHireable(Object hireable) {
            this.hireable = hireable;
        }

        public Object getBio() {
            return bio;
        }

        public void setBio(Object bio) {
            this.bio = bio;
        }

        public Integer getPublicRepos() {
            return publicRepos;
        }

        public void setPublicRepos(Integer publicRepos) {
            this.publicRepos = publicRepos;
        }

        public Integer getPublicGists() {
            return publicGists;
        }

        public void setPublicGists(Integer publicGists) {
            this.publicGists = publicGists;
        }

        public Integer getFollowers() {
            return followers;
        }

        public void setFollowers(Integer followers) {
            this.followers = followers;
        }

        public Integer getFollowing() {
            return following;
        }


        public void setFollowing(Integer following) {
            this.following = following;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public ZonedDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

}
