package com.app.toolrental.models;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tool {
	@Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;	
	@Column(unique = true, nullable = false)
	private String code;	// Unique code name for tool
	@Column(nullable = false)
    private String type;  // Type of tool
	@Column(nullable = false)
    private String brand; // Brand name of the tool
	@Column(nullable = false)
    private double dailyCharge; // Daily charge of the tool eg: 33.2
	@Column(nullable = false)
    private boolean isWeekdayChargeable; // Indicates if the tool has weekday charges
	@Column(nullable = false)
    private boolean isWeekendChargeable; // Indicates if the tool has weekend charges
	@Column(nullable = false)
    private boolean isHolidayChargeable;  // Indicates if the tool has holiday charges
	@CreationTimestamp
    @Column(updatable = false)
    Timestamp dateCreated; // Time stamp of when the tool was first created.
    @UpdateTimestamp
    Timestamp lastModified; // Time stamp of when the tool was last updated.

}
