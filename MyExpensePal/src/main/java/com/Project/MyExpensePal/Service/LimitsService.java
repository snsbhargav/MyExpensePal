package com.Project.MyExpensePal.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Project.MyExpensePal.Entity.LimitsEntity;

@Service
public interface LimitsService {

	public ResponseEntity<String> saveLimit(LimitsEntity limitsEntity);

	public ResponseEntity<String> updateLimit(LimitsEntity limitsEntity);
}
