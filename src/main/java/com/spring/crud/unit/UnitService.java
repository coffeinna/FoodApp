package com.spring.crud.unit;

import java.util.List;

public interface UnitService {
	
	List<Unit> getAllUnits();

	void saveUnit(Unit unit);

	Unit getUnitById(Integer id);

	void deleteUnitById(Integer id);
}
