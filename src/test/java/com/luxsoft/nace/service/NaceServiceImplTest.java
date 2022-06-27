package com.luxsoft.nace.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luxsoft.nace.entity.NaceEntity;
import com.luxsoft.nace.repository.NaceDAO;
import com.luxsoft.nace.service.impl.NaceServiceImpl;

@ExtendWith(MockitoExtension.class)
public class NaceServiceImplTest {

	@Mock
	private NaceDAO naceDao;

	@InjectMocks
	private NaceServiceImpl naceService;

	private NaceEntity naceEntity;

	@Test
	public void testGetNaceDetailsByIdSuccess() {
		naceEntity = new NaceEntity("1", "2", "3", "Nace 1", "ref");
		when(naceDao.getNaceByOrder("1")).thenReturn(Optional.of(naceEntity));
		Assertions.assertEquals(naceEntity, naceService.getNaceDetailsById("1"), "Nace entity should be as expected");
	}

	@Test
	public void testGetNaceDetailsByIdFailure() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			when(naceDao.getNaceByOrder("1")).thenThrow(EntityNotFoundException.class);
			naceService.getNaceDetailsById("1");
		});
	}

}