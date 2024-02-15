package rest;

import context.ContextHolder;

import static io.restassured.RestAssured.given;
import static io.restassured.http.Method.GET;

public class JokeService extends BaseRestService {
    public JokeService(ContextHolder contextHolder) {
        super(contextHolder);
    }

    public void getRandomJoke() {
        request(
                given(),
                GET,
                "/random_joke"
        );
    }
}
