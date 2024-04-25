package com.app.toolrental;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.toolrental.service.ToolRentalService;

@SpringBootTest
// Test class for the ToolRentalService checkout class
public class ToolRentalServiceTests {

	@Autowired
    private ToolRentalService toolRentalService; // Instance of toolRentalService for testing

    
    // Test case for checkout method - Scenario 1
    @Test
    void testCheckout_Scenario1() {
        // Perform checkout operation and get rental agreement
        boolean status = toolRentalService.checkoutTool("JAKR", 5, 101, LocalDate.of(2024, 9, 2));
        assertThat(status).isEqualTo(false);
        
    }

    // Test case for checkout method - Scenario 2
    @Test
    void testCheckout_Scenario2() {
    	 boolean status = toolRentalService.checkoutTool("LADW", 0, 0, LocalDate.of(2024, 9, 1));
    	 assertThat(status).isEqualTo(false);
    }

    // Test case for checkout method - Scenario 3
    @Test
    void testCheckout_Scenario3() {
    	 boolean status = toolRentalService.checkoutTool("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
    	 assertThat(status).isEqualTo(true);
    }

    // Test case for checkout method - Scenario 4
    @Test
    void testCheckout_Scenario4() {
    	 boolean status = toolRentalService.checkoutTool("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
    	 assertThat(status).isEqualTo(true);
    }

    // Test case for checkout method - Scenario 5
    @Test
    void testCheckout_Scenario5() {
    	 boolean status = toolRentalService.checkoutTool("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
    	 assertThat(status).isEqualTo(true);
    }

    // Test case for checkout method - Scenario 6
    @Test
    void testCheckout_Scenario6() {
    	 boolean status = toolRentalService.checkoutTool("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
    	 assertThat(status).isEqualTo(true);
    }
}

