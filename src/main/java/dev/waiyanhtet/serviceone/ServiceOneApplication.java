package dev.waiyanhtet.serviceone;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
@SpringBootApplication
public class ServiceOneApplication {

    private static final Logger log = LoggerFactory.getLogger(ServiceOneApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceOneApplication.class, args);
    }

    @GetMapping(value = "/greet")
    public ResponseEntity<String> greet(HttpServletRequest request) {
        log.info("Gateway request header value : {}", request.getHeader("gateway-req-header"));
        return ResponseEntity.ok("Hello from Service One");
    }

    @PostMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloResponse> hello(@RequestBody HelloRequest helloRequest, HttpServletRequest request) {
        log.info("Gateway request header value : {}", request.getHeader("gateway-req-header"));
        return ResponseEntity.ok(new HelloResponse("Hello", helloRequest.name()));
    }

    public record HelloRequest(String name, String address) {}

    public record HelloResponse(String greet, String name) {}

}
