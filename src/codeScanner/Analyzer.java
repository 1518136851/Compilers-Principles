package codeScanner;

import java.io.*;
import java.util.*;
import codeScanner.*;

public class Analyzer {
	private File inputFile;
	private String fileContent;
	private ArrayList<Word> list = new ArrayList<>();
 
	public Analyzer(String input) { 
		inputFile = new File(input);
	}
	/**
	 * ��ָ�����ļ��ж�ȡԴ�����ļ�����
	 * @return
	 */
	public String getContent() {
		StringBuilder stringBuilder = new StringBuilder();
		try(Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				stringBuilder.append(line + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileContent = stringBuilder.toString();
	}
	/**
	 * �Ƚ�Դ�����е�ע�ͺͻ����滻�ɿմ�
	 * Ȼ��ɨ������ڳ������ǰ��ɨ�赽�Ĵ���ӵ�list��
	 */
	public void analyze(String fileContent) {
		int over = 1;
		int line =0;
		Word word = new Word();
		CodeScanner scanner = new CodeScanner(fileContent.toCharArray());
		while (over != 0) {
			word = scanner.scan();
			over = word.getTypenum();
			if (over == 0){
				list.add(word);
				break;
			}
			if (word.getTypenum() == 40) {
				list.add(word);
				line++;
			}
			if (word.getTypenum()==-1) {
				System.out.println("line"+line+" is error.");
				break;
			}
//			System.out.println("(" + word.getTypenum() + " ," + word.getWord() + ")"); //�ڿ���̨������
			list.add(word);
		}
	}
	
	public ArrayList<Word> getList() {
		return list;
	}
}
