package com.app.toolrental.service;

import java.time.LocalDate;
import java.util.List;

import com.app.toolrental.models.RentalAgreement;
import com.app.toolrental.models.Tool;

public interface ToolRentalService {
	
	List<Tool> getTools();

	Tool getToolById(Long id);
	
	Tool getToolByCode(String code);

	Tool insert(Tool tool);

    void updateTool(Long id, Tool tool);

    void deleteTool(Long toolId);

    RentalAgreement checkoutTool(String toolCode, int rentalDays, int discountPercentage, LocalDate checkoutDate);

}
