package com.jnitsample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class ShapesTest {
    
    @Test
    void test(){
        assertEquals(6,6);
    }

    @Test
    void testComputeSquareArea(){
        Shapes shape1 = new Shapes();
        assertEquals(25,shape1.ComputeSquareArea(5),"Square area should be 25");
    }

    @Test
    void testComputeCircleArea(){
        Shapes shape2 = new Shapes();
        assertEquals(Math.PI*4*4,shape2.ComputeCircleArea(4),"Circle area should be 16*PI");
    }
    
    @Test
    void testComputeSquareArea1(){
        Shapes shape3 = new Shapes();
        assertNotEquals(5674, shape3.ComputeSquareArea(24));
    }

        @Test
    void testComputeSquareArea_WithMessage(){
        Shapes shape3 = new Shapes();
        assertNotEquals(5674, shape3.ComputeSquareArea(24), "Square area should not be 5674");
    }

            @Test
    void testComputeSquareArea_Supplier(){
        Shapes shape3 = new Shapes();
        assertNotEquals(5674, shape3.ComputeSquareArea(24), "Square area should not be 5674");
    }
}
