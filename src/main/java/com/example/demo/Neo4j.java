package com.example.demo;

import java.util.List;


import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class Neo4j {
    private final Driver driver;

    public Neo4j(Driver driver){
        this.driver=driver;
    }

    @GetMapping(path="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMovieTitles(){
        try(Session session= driver.session()){
            return session.run("MATCH (p:information{name:'順序レベル3'})-[r:practical]->(m:information) RETURN m.name").list(r -> r.get("m.name").asString());

        }
//        try(Session session= driver.session()){
//            return session.run("MATCH (n:Python) RETURN n LIMIT 25").list(r -> r.get("n").asNode().get("name").asString());
//        }
    }

}
