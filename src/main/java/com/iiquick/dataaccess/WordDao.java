package com.iiquick.dataaccess;

import java.util.List;

import com.iiquick.domain.Word;

public interface WordDao {
	
	 public void insertEN(String enword);	
	 public void insertJK(String jkword);	
	 public void insertJM(String jmword);	
	 public void insertMM(String mmword);
	 
	 public List<Word> getAllEnword();
	 public List<Word> getAllJkword();
	 public List<Word> getAllJmword();
	 public List<Word> getAllMmword();
	 
	 public List<Word> getEnword(String enword);	 
	 public List<Word> getJkword(String jkword);
	 public List<Word> getJmword(String jmword);
	 public List<Word> getMmword(String mmword);
	 
	 public Integer getEnidByWord(String enword);
	 public Integer getJkidByWord(String jkword);	 
	 public Integer getJmidByWord(String jmword);	 
	 public Integer getMmidByWord(String mmword);
	 
	 public int countEn();	 
	 public int countJk();	 
	 public int countJm();	 
	 public int countMm();
}
