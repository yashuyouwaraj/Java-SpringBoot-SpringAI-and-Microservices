package com.service;

import com.learning.Course;

public class PurchaseCourse {

    private Course course;
    
    public boolean proceedWithCourse(Course course){
        return course.coursePurchased();
    }
}
