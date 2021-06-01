package com.techprimers.kafka.springbootkafkaproducerexample.resource;

import com.techprimers.kafka.springbootkafkaproducerexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";

    @GetMapping
    public String hello() {
        return "GET";
    }

    @GetMapping("/publish/{name}")
    public String postName(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC, new User(name, "Technology", 12000L));

        return "Published successfully";
    }

    @PostMapping("/publish")
    public String postUser(@RequestBody User user) {
        kafkaTemplate.send(TOPIC, user);
        return "Published successfully\n" + user.toString();
    }
}
