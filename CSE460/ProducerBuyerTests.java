package ProducerBuyer;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * CSE 460 Software Analysis and Design Project - Sample Test Cases
 * This class contains some test cases that will be used in automated grading of your project.
 * Note that these test cases are not exhaustive - other cases and abnormal inputs will be used as well.
 *
 * These tests concern only with the correctness and robustness of your implementation. You are still required to
 * adopt Publisher-Subscriber pattern in your design and make use of good programming practises. Otherwise you can
 * suffer heavy penalty for a 100% correct implementation!
 *
 * The undisclosed part of the test will differ from this test somewhat:
 * - Test cases are automatically generated with random characters (other than commas and leading/trailing spaces).
 * - The generated test cases can be very long and contains unusual sequences.
 * - Instead of testing the output of your program against a "standard answer", your output would be compared against
 *   the output of a "Reference Implementation" (RI), which is seen as the authoritative answer.
 *
 * @author Sheetal Mohite <smohite3@asu.edu>
 * @version 1.0
 */
public class ProducerBuyerTests {

public static void main(String[] args) {
    ProducerBuyer test = new ProducerBuyer();

    // Test 1: one subscriber and multiple publishers

// Expected output
List<String> expected = new ArrayList<>(Arrays.asList(
    "avis notified car: honda civic, hybrid fuel",
    "avis notified car: audi q5, hybrid fuel",
    "avis notified car: chevrolet general motors, hybrid fuel"));

// Feed the test object with some commands


test.processInput("subscribe, Avis, Car");
test.processInput("publish, Honda, Car, Civic, hybrid");
test.processInput("publish, Audi, Car, Q5, hybrid");
test.processInput("publish, Chevrolet, Car, general motors, hybrid");

// print the output by calling getAggregatedOutput
System.out.println("The output for test 1 is :");
test.getAggregatedOutput();

// Reset the test object every time a test finishes so that it can accept a new batch of commands
test.reset();


//Test 2: illegal parameters
    // Expected output (nothing)

    test.processInput("subscribe, Budget, Car");
    test.processInput("publish, Honda, Car, Civic, hybrid");
    test.processInput("publish, Honda, Car, Civic, hybrid, $15000.00");    

    // Obtain the actual result from your test object and compare it with the expected output
    // print the output by calling getAggregatedOutput
    System.out.println("The output for test 2 is :");
    test.getAggregatedOutput();
    
    }
}