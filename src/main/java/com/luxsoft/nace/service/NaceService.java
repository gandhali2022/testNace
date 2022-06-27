package com.luxsoft.nace.service;

import com.luxsoft.nace.entity.NaceEntity;

import java.util.List;

public interface NaceService {

	void insertNaceData(List<NaceEntity> naceDetails);

	NaceEntity getNaceDetailsById(String id);
	
}