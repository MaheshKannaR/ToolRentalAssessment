package com.app.toolrental;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.toolrental.models.RentalAgreement;
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
    	RentalAgreement rentalAgreement = toolRentalService.checkoutTool("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
        assertThat(rentalAgreement).isEqualTo(null);
        
    }

    // Test case for checkout method - Scenario 2
    @Test
    void testCheckout_Scenario2() {
    	RentalAgreement rentalAgreement = toolRentalService.checkoutTool("LADW", 0, 0, LocalDate.of(2015, 7, 2));
    	 assertThat(rentalAgreement).isEqualTo(null);
    }

    // Test case for checkout method - Scenario 3
    @Test
    void testCheckout_Scenario3() {
    	RentalAgreement rentalAgreement = toolRentalService.checkoutTool("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
    	 assertThat(rentalAgreement.getFinalCharge()).isEqualTo(3.3525);
    }

    // Test case for checkout method - Scenario 4
    @Test
    void testCheckout_Scenario4() {
    	RentalAgreement rentalAgreement = toolRentalService.checkoutTool("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
    	 assertThat(rentalAgreement.getFinalCharge()).isEqualTo(8.97);
    }

    // Test case for checkout method - Scenario 5
    @Test
    void testCheckout_Scenario5() {
    	RentalAgreement rentalAgreement = toolRentalService.checkoutTool("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
    	 assertThat(rentalAgreement.getFinalCharge()).isEqualTo(14.950000000000001);
    }

    // Test case for checkout method - Scenario 6
    @Test
    void testCheckout_Scenario6() {
    	RentalAgreement rentalAgreement = toolRentalService.checkoutTool("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
    	 assertThat(rentalAgreement.getFinalCharge()).isEqualTo(1.495);
    }
}

