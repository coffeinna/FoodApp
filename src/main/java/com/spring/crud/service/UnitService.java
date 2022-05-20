package com.spring.crud.service;

import com.spring.crud.model.Unit;

import java.util.List;

public interface UnitService {
	
	List<Unit> getAllUnits();

	void saveUnit(Unit unit);

	Unit getUnitById(Integer id);

	void deleteUnitById(Integer id);
}
