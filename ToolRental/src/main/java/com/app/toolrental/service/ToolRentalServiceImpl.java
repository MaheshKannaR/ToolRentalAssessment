package com.app.toolrental.service;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.toolrental.models.RentalAgreement;
import com.app.toolrental.models.Tool;
import com.app.toolrental.repository.ToolRepository;

@Service
public class ToolRentalServiceImpl implements ToolRentalService {
	
	ToolRepository toolRepository;

    public ToolRentalServiceImpl(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    // Gets all the tools
    @Override
    public List<Tool> getTools() {
        List<Tool> tools = new ArrayList<>();
        toolRepository.findAll().forEach(tools::add);
        return tools;
    }
    
    // Gets the tool by giving its id
    @Override
    public Tool getToolById(Long id) {
        return toolRepository.findById(id).get();
    }
    
    // Gets the tool by giving its code
    @Override
    public Tool getToolByCode(String code) {
    	List<Tool> tools = toolRepository.findByCode(code);
    	if(tools.size() > 0) {
    		return tools.get(0);
    	}
    	return null;        
    }
    
    // Inserts the tool in DB
    @Override
    public Tool insert(Tool tool) {
    	tool.setDateCreated(new Timestamp(System.currentTimeMillis()));
        return toolRepository.save(tool);
    }
    
    // Updates the tool in DB, by giving it id and the new details of the tool
    @Override
    public void updateTool(Long id, Tool tool) {
    	Tool toolFromDb = toolRepository.findById(id).get();
    	toolFromDb.setBrand(tool.getBrand());
    	toolFromDb.setType(tool.getType());
    	toolFromDb.setDailyCharge(tool.getDailyCharge());
    	toolFromDb.setWeekendChargeable(tool.isWeekendChargeable());
    	toolFromDb.setWeekdayChargeable(tool.isWeekdayChargeable());
    	toolFromDb.setHolidayChargeable(tool.isHolidayChargeable());
    	toolFromDb.setLastModified(new Timestamp(System.currentTimeMillis()));
    	toolRepository.save(toolFromDb);
    }
    
    // Deletes the tool from DB, by giving its id
    @Override
    public void deleteTool(Long toolId) {
    	toolRepository.deleteById(toolId);
    }
    
    // Validates the inputs given
    private boolean validateInputs(int rentalDays, int discountPercentage) {
		if (rentalDays < 1) { // Rental day should be greater than 0
			System.out.println("Please rent the tool for atleast one day");
			return false;
		}
		if (discountPercentage < 0 || discountPercentage > 100) { // Discount % should be between 0 & 100
			System.out.println("Discount percetage should be between the range of 0% and 100%");
			return false;
		}
		return true;
	}
    
    // Check if the given date is a holiday
    private boolean isHoliday(LocalDate date) {
		LocalDate independenceDay = LocalDate.of(date.getYear(), 7, 4); // Independence Day
		LocalDate nextDay = date.plusDays(1);
		LocalDate previousDay = date.minusDays(1);
		boolean isAdditionalHoliday = false;
		// If Independence day falls on Sat consider the before day as holiday
		if (nextDay.equals(independenceDay) && independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
			isAdditionalHoliday = true;
		}
		
		// If Independence day falls on Sun consider the next day as holiday
		if (previousDay.equals(independenceDay) && independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
			isAdditionalHoliday = true;
		}
		// First Monday of September is considered as holiday
		LocalDate laborDay = LocalDate.of(date.getYear(), 9, 1).with(firstInMonth(DayOfWeek.MONDAY));
		return date.equals(independenceDay) || date.equals(laborDay) || isAdditionalHoliday;
	}

    // Checks if the given date is a weekend
	private boolean isWeekend(LocalDate date) {
		DayOfWeek day = date.getDayOfWeek();
		return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
	}

	// Check if the given date is chargeable 
	private boolean isDateChargeable(LocalDate date, Tool tool) {		
		if (isWeekend(date) && !tool.isWeekendChargeable()) {
			return false;
		}
		if (isHoliday(date) && !tool.isHolidayChargeable()) {
			return false;
		}
		return true;
	}

	// Calculates the charge dates between the checkout date & due date
	private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tool tool) {
		int chargeDays = 0;
		for (LocalDate date = checkoutDate.plusDays(1); date.isBefore(dueDate.plusDays(1)); date = date.plusDays(1)) {
			if (isDateChargeable(date, tool)) {
				chargeDays++;
			}
		}
		return chargeDays;
	}

	// Calculates the amount before applying the discount
	private double calculatePreDiscountCharge(int noOfChargeDays, double dailyRentalCharge) {
		return noOfChargeDays * dailyRentalCharge;
	}

	// Calculates the dicount amount
	private double calculateDiscountAmount(int discountPercentage, double preDiscountCharge) {
		return (discountPercentage / 100.0) * preDiscountCharge;
	}

	// Calculates the final amount by applying the discount
	private double calculateFinalCharge(double preDiscountCharge, double discountAmount) {
		return preDiscountCharge - discountAmount;
	}
    
    @Override
    // Checks out the tool and prints the rental agreement.
    public boolean checkoutTool(String toolCode, int rentalDays, int discountPercentage, LocalDate checkoutDate) {
    		
    	if(validateInputs(rentalDays, discountPercentage)) {
    		Tool tool = getToolByCode(toolCode);
    		if(!ObjectUtils.isEmpty(tool)) {
    			LocalDate dueDate = checkoutDate.plusDays(rentalDays);
    			int noOfChargeDays = calculateChargeDays(checkoutDate, dueDate, tool);
    			double preDiscountCharge = calculatePreDiscountCharge(noOfChargeDays, tool.getDailyCharge());
    			double discountAmount = calculateDiscountAmount(discountPercentage, preDiscountCharge);
    			double finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);
    		    			
    			RentalAgreement rentalAgreement = new RentalAgreement(tool.getCode(), tool.getType(), tool.getBrand(), 
    					rentalDays, discountPercentage, checkoutDate, dueDate, tool.getDailyCharge(), noOfChargeDays,
    					preDiscountCharge, discountAmount, finalCharge);
    			rentalAgreement.printAgreement();
    			return true;
    			
    		} else {
    			System.out.println("Tool not found for the given code");
    			return false;
    		}
    		
    	} 
    	return false;
    }

}
