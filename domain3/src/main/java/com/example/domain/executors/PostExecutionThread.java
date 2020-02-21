package com.example.domain.executors;


import io.reactivex.Scheduler;

/*
Created by Liza Antoun 11/12/2019
 */
public interface PostExecutionThread {
    /**
     * PostExecutionThread returns Schedular which is a tool that we can use for scheduling individual actions.
     */
    Scheduler getScheduler();
}
