package com.luxsoft.nace.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxsoft.nace.entity.NaceEntity;
import com.luxsoft.nace.repository.NaceDAO;
import com.luxsoft.nace.service.NaceService;

@Service
public class NaceServiceImpl implements NaceService {

	private NaceDAO naceDao;

	@Autowired
	public NaceServiceImpl(NaceDAO naceDao) {
		this.naceDao = naceDao;
	}

	/**
	 * save nace data
	 */
	@Override
	public void insertNaceData(List<NaceEntity> naceDetails) {
		naceDao.saveAll(naceDetails);
	}

	/**
	 * extract nace data
	 */
	@Override
	public NaceEntity getNaceDetailsById(String order) {
		return naceDao.getNaceByOrder(order).orElseThrow(EntityNotFoundException::new);
	}
}