package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.vo.ExerciseVO;

@Service
public class ExerciseDataService {
	
	@Autowired
	private ExerciseVO evo;
	
	@Autowired
	private ExerciseMapper exerciseMapper;
	
	public List<ExerciseVO> getExerciseRecode(long getListStart, int listLen, String uid) {
		List<ExerciseVO> result = null;
		
		evo.seteNo(getListStart);
		evo.setCnt(listLen);
		evo.setUserId(uid);
		
		result = exerciseMapper.select_exerciseRecode_withUid_andRange(evo);
		
		return result;
	}
}
