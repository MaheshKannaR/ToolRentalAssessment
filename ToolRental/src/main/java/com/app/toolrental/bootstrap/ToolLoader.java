package com.app.toolrental.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.toolrental.models.Tool;
import com.app.toolrental.repository.ToolRepository;

@Component
// Initial load to be done in the DB
public class ToolLoader implements CommandLineRunner {
	
	public final ToolRepository toolRepository;

    public ToolLoader(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadTools();
    }

    private void loadTools() {
        if (toolRepository.count() == 0) {
        	toolRepository.save(
                    Tool.builder()
                            .code("CHNS")
                            .type("Chainsaw")
                            .brand("Stihl")
                            .dailyCharge(1.49)
                            .isWeekdayChargeable(true)
                            .isWeekendChargeable(false)
                            .isHolidayChargeable(true)
                            .build()
                            
            );
        	toolRepository.save(
                    Tool.builder()
                            .code("LADW")
                            .type("Ladder")
                            .brand("Werner")
                            .dailyCharge(1.99)
                            .isWeekdayChargeable(true)
                            .isWeekendChargeable(true)
                            .isHolidayChargeable(false)
                            .build()
                            
            );
        	toolRepository.save(
                    Tool.builder()
                            .code("JAKD")
                            .type("Jackhammer")
                            .brand("DeWalt")
                            .dailyCharge(2.99)
                            .isWeekdayChargeable(true)
                            .isWeekendChargeable(false)
                            .isHolidayChargeable(false)
                            .build()
                            
            );
        	toolRepository.save(
                    Tool.builder()
                            .code("JAKR")
                            .type("Ridgid")
                            .brand("DeWalt")
                            .dailyCharge(2.99)
                            .isWeekdayChargeable(true)
                            .isWeekendChargeable(false)
                            .isHolidayChargeable(false)
                            .build()
                            
            );
            System.out.println("Sample Tools Loaded");
        }
    }

}
