package com.rydata.reative.completeablefuture;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/cf/demo")
public class CompletableFutureDemoController {
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResponseDAO> testMethod(){
        List<CompletableFuture<ResponseDAO>> responseDtos = new ArrayList<>();
        responseDtos.add(CompletableFuture.supplyAsync(() -> sendEmail("mail-a")));
        responseDtos.add(CompletableFuture.supplyAsync(() -> sendEmail("mail-b")));
        responseDtos.add(CompletableFuture.supplyAsync(() -> sendEmail("mail-c")));
        responseDtos.add(CompletableFuture.supplyAsync(() -> sendEmail("mail-d")));
        responseDtos.add(CompletableFuture.supplyAsync(() -> sendEmail("mail-e")));
        responseDtos.add(CompletableFuture.supplyAsync(() -> sendEmail("mail-f")));
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(responseDtos.toArray(new CompletableFuture[0]));
        CompletableFuture<List<ResponseDAO>> allCFutures = allFutures.thenApply(future -> responseDtos.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        CompletableFuture<List<ResponseDAO>> listCompletableFuture = allCFutures.toCompletableFuture();
        try {
            List<ResponseDAO> responseDTOList=listCompletableFuture.get();
            return responseDTOList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResponseDAO sendEmail(String email){
        try{
            System.out.println(Thread.currentThread().getName());
            System.out.println("Sending email to: " + email);
            Thread.sleep(1000);
            System.out.println("email sent");
            return new ResponseDAO(email, true);
        } catch(InterruptedException ex){
            ex.printStackTrace();
            return new ResponseDAO(email, false);
        }
    }
}
