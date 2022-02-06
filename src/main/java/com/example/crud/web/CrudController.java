package com.example.crud.web;

import an.awesome.pipelinr.Pipeline;
import com.example.crud.service.GetUserByName;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrudController {

    private final Pipeline pipeline;
    private final ApplicationContext context;

    public CrudController(Pipeline pipeline, ApplicationContext context) {
        this.pipeline = pipeline;
        this.context = context;
    }

    @GetMapping("/")
    public String[] hello() {

        var user = new GetUserByName("name").execute(pipeline);

        return context.getBeanDefinitionNames();
    }
}
