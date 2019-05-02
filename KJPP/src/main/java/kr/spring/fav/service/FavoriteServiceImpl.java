
package kr.spring.fav.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.fav.dao.FavoriteMapper;
import kr.spring.fav.domain.FavoriteCommand;

@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {
	
	@Resource
	private FavoriteMapper favoriteMapper;
	
	@Override
	public List<FavoriteCommand> selectList1(Map<String, Object> map) {
		return favoriteMapper.selectList1(map);
	}

	@Override
	public int selectRowCount1(Map<String, Object> map) {
		return favoriteMapper.selectRowCount1(map);
	}
	@Override
	public List<FavoriteCommand> selectList2(Map<String, Object> map2) {
		return favoriteMapper.selectList2(map2);
	}
 
	@Override
	public int selectRowCount2(Map<String, Object> map2) {
		return favoriteMapper.selectRowCount2(map2);
	}

	@Override
	public List<FavoriteCommand> selectList3(Map<String, Object> map3) {
		return favoriteMapper.selectList3(map3);
	}

	@Override
	public int selectRowCount3(Map<String, Object> map3) {
		return favoriteMapper.selectRowCount3(map3);
	}

	
	@Override
	public List<FavoriteCommand> selectFav1() {
		return favoriteMapper.selectFav1();
	}
	
	@Override
	public List<FavoriteCommand> selectFav2() {
		return favoriteMapper.selectFav2();
	}

	@Override
	public List<FavoriteCommand> selectFav3() {
		return favoriteMapper.selectFav3();
	}
	
	
	
	@Override
	public void insertPlace(FavoriteCommand favorite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertPlan(FavoriteCommand favorite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertMember(FavoriteCommand favorite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFav(Integer f_num) {
		favoriteMapper.deleteFav(f_num);
		
	}

	@Override
	public List<FavoriteCommand> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}


}
