package dev.umang.productcatalog.controllers;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.umang.productcatalog.bean.Greeting;

@RestController
public class TestController {

      private AtomicInteger id = new AtomicInteger();
      
      @GetMapping("/hi")
      public Greeting hiEveryone(@RequestParam(name = "user", defaultValue = "Everyone") String mString){
            String msg = "Hii "+mString;
            //System.out.println(msg);

            Greeting greeting = new Greeting(id.incrementAndGet(), msg);
            

            return greeting;
      }

}
