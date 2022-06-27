package com.luxsoft.nace.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.luxsoft.nace.entity.NaceEntity;

@SpringBootTest
public class NaceDaoTest {

	@Autowired
	private NaceDAO naceDao;

	@AfterEach
	public void cleanup() {
		naceDao.deleteAll();
	}

	@Test
	public void testPersistence() {
		NaceEntity savedObject = new NaceEntity();
		savedObject.setDescription("test");
		savedObject.setCode("123");
		savedObject.setLevel("1");
		savedObject.setOrder("1");

		naceDao.save(savedObject);

		assertNotNull(savedObject.getId());
		NaceEntity naceEntity = naceDao.findById(savedObject.getId()).orElse(null);
		assertEquals("test", naceEntity.getDescription());
		assertEquals("123".compareTo(naceEntity.getCode()), 0);
		assertEquals("1", naceEntity.getLevel());
	}

}