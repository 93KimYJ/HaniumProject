package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.vo.ExerciseVO;

@Service
public class ExerciseDataService {
	
	private long MAX_LONG = 9223372036854775807L;
	
	@Autowired
	private ExerciseMapper exerciseMapper;
	
	/**
	 * 운동기록의 시작 번호와 가져올 번호 수를 받아서 레코드를 가져옴
	 * ex) 20, 5 -> 20, 19, 18, 17, 16
	 * 운동기록 시작에 -1 쓰면 가장 최신값부터 가져옴
	 * @param getListStart
	 * @param listLen
	 * @param uid
	 * @return
	 */
	public List<ExerciseVO> getExerciseRecode(long getListStart, int listLen, String uid) {
		ExerciseVO evo = new ExerciseVO();
		List<ExerciseVO> result = null;
		
		if(getListStart < 0) {
			getListStart = MAX_LONG;
		}
		
		evo.seteNo(getListStart);
		evo.setCnt(listLen);
		evo.setUserId(uid);
		
		result = exerciseMapper.select_exerciseRecode_withUid_andRange(evo);
		
		return result;
	}
	
	public List<ExerciseVO> getExerciseRecode(long getListStart, int listLen, String uid, String type) {
		ExerciseVO evo = new ExerciseVO();
		List<ExerciseVO> result = null;
		
		if(getListStart < 0) {
			getListStart = MAX_LONG;
		}
		
		evo.seteNo(getListStart);
		evo.setCnt(listLen);
		evo.setUserId(uid);
		evo.setType(type);
		
		System.out.println("오류나면 아이디 세션에 값 없어서임");
		
		result = exerciseMapper.select_exerciseRecode_withUid_andType_andRange(evo);
		
		return result;
	}
	
	public List<ExerciseVO> getBestExerciseRecodeWithType(String type, int num) {
		ExerciseVO vo = new ExerciseVO();
		vo.setType(type);
		vo.setCnt(num);
		
		List<ExerciseVO> bestList = exerciseMapper.select_allTime_bestExerciseRecode_withType_andRownum(vo);
		
		return bestList;
	}
}
