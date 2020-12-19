package codeScanner;

import java.util.ArrayList;
//(������󣬷��ţ���)

public class Semantics {
	private Analyzer analyzer;
	private ArrayList<Word> list = new ArrayList<>();
	private Word word;
	private int index = 0; // ���б��л�ȡ���ʵ��±�
	private boolean error = false;
	private int line = 1;
	
	public Semantics() {
		analyzer = new Analyzer("input.txt");
		analyzer.analyze(analyzer.getContent());
		list = analyzer.getList();
	}
	
	public void parse() {
		word = getNext(list);
		if (word.getTypenum() == 9) {
			word = getNext(list);
			yucu();
			if (word.getTypenum() == 0 && !error) {
//				��ȫû�д��
				System.out.println("success");
			}
		}else {
			error = true;
			System.out.println("��"+line+"��"+"begin����");
			word = getNext(list);
			while(word.getTypenum() != 40) {
				word = getNext(list);
			}
		}
	}
	
	public void yucu() {
		statement();
		while (word.getTypenum() == 26||				
				word.getTypenum() ==50||
				word.getTypenum() ==51||
				word.getTypenum() ==40) {
			word = getNext(list);
			statement();
		}
	}
	
	public void statement() {
//		ע�Ͳ��ù�
		if (word.getTypenum() ==50||word.getTypenum() ==51) {
			return;
		}
//		�س����ù�
		else if (word.getTypenum() ==40){
			return;
		}
		else if(word.getTypenum() ==0) {
			return;
		}
		String tt,eplace;
		tt=word.getWord();//�����tt�Ǳ�ʶ��
		word = getNext(list);
		if (word.getTypenum() ==25) {//�Ⱥ�
			word = getNext(list);
			eplace = expression();
//			������Ԫʽ
			System.out.println("("+tt+" "+eplace+" "+"="+" "+""+")");
		}
		else {
			System.out.println("ȱ�ٸ�ֵ�Ŵ���");
			error = true;
		}
	}
//	�Ӽ�
	public String expression() {
		String tp,ep2,eplace,tt;
		int ttnum = 0;
		eplace = term();
		while(word.getTypenum() ==13 ||word.getTypenum() ==14) {
			tt = word.getWord();
			ttnum = word.getTypenum();
			word = getNext(list);
			ep2 = term();
			try {
				if (ttnum == 13) {
					tp = String.valueOf(Integer.parseInt(eplace)+Integer.parseInt(ep2));
				}
				else {
					tp = String.valueOf(Integer.parseInt(eplace)-Integer.parseInt(ep2));
				}
				System.out.println("("+tp+" "+eplace+" "+tt+" "+ep2+")");
				eplace = tp;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error");
				break;
			}
		}
		return eplace;
	}
//	�˳�
	public String term() {
		String tp,ep2,eplace,tt;
		int ttnum = 0;
		eplace = factory();
		while(word.getTypenum() == 15 || word.getTypenum() == 16) {
			tt = word.getWord();
			ttnum = word.getTypenum();
			word = getNext(list);
			ep2 = factory();
			if (ttnum == 15) {
				tp = String.valueOf(Integer.parseInt(eplace)*Integer.parseInt(ep2));
			}
			else {
				tp = String.valueOf(Integer.parseInt(eplace)/Integer.parseInt(ep2));
			}
			System.out.println("("+tp+" "+eplace+" "+tt+" "+ep2+")");
			eplace = tp;
		}
		return eplace;
	}
	
	public String factory() {
		String fplace;
		fplace = "";
		if (word.getTypenum() == 10) { //����ĸ
			fplace = word.getWord();
			word = getNext(list);
		}
		else if (word.getTypenum() ==11) { //������
//			fplace=String.valueOf(word.getWord());
			fplace = word.getWord();
			word = getNext(list);
		}
//		������������չ�����ŵ�ʱ������
//		else if() {
//			 
//		}
		else {
			System.out.println("����");
			error = true;
		}
		return fplace;
	}
	
	public Word getNext(ArrayList<Word> list) {
		if (index < list.size()) {
			return list.get(index++);
		} else {
			return null;
		}
	}
}
