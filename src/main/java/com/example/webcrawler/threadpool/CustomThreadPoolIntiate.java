package com.example.webcrawler.threadpool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class CustomThreadPoolIntiate implements CommandLineRunner {

    public static  final ExecutorService  threadPool =  new ThreadPoolExecutor(200, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    @Override
    public void run(String... args) throws Exception {
        startThreadPool();
    }
    public void startThreadPool() {
        int threadNumber = 200;
    }

}
