package kr.spring.location.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.fav.domain.FavoriteCommand;
import kr.spring.location.dao.LocationMapper;
@Service("locationService")
public class LocationServiceImpl implements LocationService{

	@Resource
	private LocationMapper locationMapper;

	@Override
	public void insert(Map<String, Object> map) {
		 locationMapper.insert(map);
		// TODO Auto-generated method stub
		
	}

	@Override
	public FavoriteCommand select(Map<String, Object> map) {
		return locationMapper.select(map);
		
	}



	



}
