package com.app.toolrental.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.toolrental.models.Tool;

@Repository
public interface ToolRepository extends CrudRepository<Tool, Long>{
	// To get the tool by giving its tool code
	List<Tool> findByCode(String code);

}
