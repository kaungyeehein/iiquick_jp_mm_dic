package com.iiquick.dataaccess;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.iiquick.domain.JMR;
import com.iiquick.domain.Word;
import com.mayhoo.kanji.JapaneseString;

public class WordDaoTemplate extends SqlMapClientTemplate implements WordDao {

	/**
	 * Insert new word
	 */
	@Override
	synchronized public void insertEN(String enword) {
		insert("Word.insertEN", enword);
	}
	@Override
	synchronized public void insertJK(String jkword) {
		insert("Word.insertJK", jkword);
	}
	@Override
	synchronized public void insertJM(String jmword) {
		JMR jmr = new JMR();
		jmr.setJmword(jmword);
		jmr.setJmromaji(JapaneseString.toRomaji(jmword));
		insert("Word.insertJM", jmr);
	}
	@Override
	synchronized public void insertMM(String mmword) {
		insert("Word.insertMM", mmword);
	}
	
	/**
	 * Get all word for Json
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getAllEnword() {
		return ((List<Word>)queryForList("Word.getAllEnword", null));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getAllJkword() {
		return ((List<Word>)queryForList("Word.getAllJkword", null));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getAllJmword() {
		return ((List<Word>)queryForList("Word.getAllJmword", null));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getAllMmword() {
		return ((List<Word>)queryForList("Word.getAllMmword", null));
	}

	/**
	 * Get word id by word string
	 */
	@Override
	public Integer getEnidByWord(String enword) {
		return ((Integer)queryForObject("Word.getEnidByWord", enword));
	}
	@Override
	public Integer getJkidByWord(String jkword) {
		return ((Integer)queryForObject("Word.getJkidByWord", jkword));
	}
	@Override
	public Integer getJmidByWord(String jmword) {
		return ((Integer)queryForObject("Word.getJmidByWord", jmword));
	}
	@Override
	public Integer getMmidByWord(String mmword) {
		return ((Integer)queryForObject("Word.getMmidByWord", mmword));
	}
	
	/**
	 * Get word list for auto complete search text box
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getEnword(String enword) {
		return ((List<Word>)queryForList("Word.getEnword", enword));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getJkword(String jkword) {
		return ((List<Word>)queryForList("Word.getJkword", jkword));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getJmword(String jmword) {
		return ((List<Word>)queryForList("Word.getJmword", jmword));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Word> getMmword(String mmword) {
		return ((List<Word>)queryForList("Word.getMmword", mmword));
	}

	/**
	 * Get number of word count
	 */
	@Override
	public int countEn() {
		return ((Integer)queryForObject("Word.countEn", null));
	}
	@Override
	public int countJk() {
		return ((Integer)queryForObject("Word.countJk", null));
	}
	@Override
	public int countJm() {
		return ((Integer)queryForObject("Word.countJm", null));
	}
	@Override
	public int countMm() {
		return ((Integer)queryForObject("Word.countMm", null));
	}

}
