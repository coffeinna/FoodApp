package com.spring.crud.unit;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {

	@Autowired
	private UnitRepository unitRepository;
	
	public UnitServiceImpl(UnitRepository unitRepository) {
		this.unitRepository = unitRepository;
	}
	
	@Override
	public List<Unit> getAllUnits() {
		return unitRepository.findAll();
	}

	@Override
	public void saveUnit(Unit unit) {
		unitRepository.save(unit);
		
	}

	@Override
	public Unit getUnitById(Integer id) {
		Optional<Unit> optional = unitRepository.findById(id);
		Unit unit = null;
		if (optional.isPresent()) {
			unit = optional.get();
		} else {
			throw new RuntimeException(" Unit not found for id :: " + id);
		}
		return unit;
	}

	@Override
	public void deleteUnitById(Integer id) {
		this.unitRepository.deleteById(id);
	}

}
