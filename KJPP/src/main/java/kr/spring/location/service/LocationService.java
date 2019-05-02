package kr.spring.location.service;

import java.util.Map;

import kr.spring.fav.domain.FavoriteCommand;

public interface LocationService {

public void insert(Map<String, Object> map);
public FavoriteCommand select(Map<String, Object> map);
}
