package com.apap.tutorial4.service;

import java.util.Optional;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.DealerDb;

public interface DealerService {
	Optional<DealerModel> getDealerDetailByID(Long id);
	
	void addDealer(DealerModel dealer);
	void deleteDealer(DealerModel dealer);
	DealerDb searchAll();

	void dealerUpdate(DealerModel dealerr, Long dealerId);
	
}
