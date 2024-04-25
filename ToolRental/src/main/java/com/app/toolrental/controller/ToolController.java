package com.app.toolrental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.toolrental.models.RentalAgreement;
import com.app.toolrental.models.Tool;
import com.app.toolrental.requestbody.CheckOutToolRequestBody;
import com.app.toolrental.service.ToolRentalService;

@RestController
@RequestMapping("/api/v1/tool")
public class ToolController {

	ToolRentalService toolRentalService;

	public ToolController(ToolRentalService toolRentalService) {
		this.toolRentalService = toolRentalService;
	}

	// API to get all the tools
	@GetMapping
	public ResponseEntity<List<Tool>> getAllTools() {
		List<Tool> tools = toolRentalService.getTools();
		return new ResponseEntity<>(tools, HttpStatus.OK);
	}

	// API to get one tool by providing its id
	@GetMapping({ "/{toolId}" })
	public ResponseEntity<Tool> getTodoById(@PathVariable Long toolId) {
		return new ResponseEntity<>(toolRentalService.getToolById(toolId), HttpStatus.OK);
	}

	// API to get one tool by providing its code
	@GetMapping({ "/code/{toolCode}" })
	public ResponseEntity<Tool> getTodoByCode(@PathVariable String toolCode) {
		return new ResponseEntity<>(toolRentalService.getToolByCode(toolCode), HttpStatus.OK);
	}

	// API to create a tool
	@PostMapping
	public ResponseEntity<Tool> saveTool(@RequestBody Tool tool) {
		Tool too1Created = toolRentalService.insert(tool);
		return new ResponseEntity<>(too1Created, HttpStatus.CREATED);
	}

	// API to updates the Tool with the specified Id and returns the updated tool
	@PutMapping({ "/{toolId}" })
	public ResponseEntity<Tool> updateTodo(@PathVariable("toolId") Long toolId, @RequestBody Tool tool) {
		toolRentalService.updateTool(toolId, tool);
		return new ResponseEntity<>(toolRentalService.getToolById(toolId), HttpStatus.OK);
	}

	// API to delete the toll with the specified Id.
	@DeleteMapping({ "/{toolId}" })
	public ResponseEntity<Tool> deleteTodo(@PathVariable("toolId") Long toolId) {
		toolRentalService.deleteTool(toolId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// API to checkout the tool and get the rental agreement details
	@PostMapping({ "/checkoutTool" })
	public ResponseEntity<RentalAgreement> checkoutTool(@RequestBody final CheckOutToolRequestBody checkOutToolRequestBody) {
		RentalAgreement rentalAgreement = toolRentalService.checkoutTool(checkOutToolRequestBody.getToolCode(), checkOutToolRequestBody.getRentalDays(), checkOutToolRequestBody.getDiscountPercentage(), checkOutToolRequestBody.getCheckoutDate());
		return new ResponseEntity<RentalAgreement>(rentalAgreement, HttpStatus.OK);
	}

}
