package com.Project.MyExpensePal.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Project.MyExpensePal.Entity.LimitsEntity;
import com.Project.MyExpensePal.Repository.LimitsRepository;
import com.Project.MyExpensePal.Service.LimitsService;

@Service
public class LimitsServiceImpl implements LimitsService{
	
	@Autowired
	private LimitsRepository limitsRepository;

	public ResponseEntity<String> saveLimit(LimitsEntity limitsEntity){
		limitsRepository.save(limitsEntity);
		return new ResponseEntity<String>("Limit set successfully.", HttpStatus.OK);
	}

	public ResponseEntity<String> updateLimit(LimitsEntity limitsEntity) {
		limitsRepository.save(limitsEntity);
		return new ResponseEntity<String>("Limit updated successfully.", HttpStatus.OK);
	}
}
