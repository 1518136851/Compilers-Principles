package codeScanner;

public class Main {
	public static void main(String[] args) {
//		Analyzer analyzer = new Analyzer("input.txt");//����������Լ��޸ģ��ļ����ڵ�ǰ�ļ����£�ˢ����Ŀ�Ϳ��Կ�����
//		analyzer.analyze(analyzer.getContent());
//		IrParser irParser = new IrParser();
//		irParser.parse();
		Semantics semantics = new Semantics();
		semantics.parse();
	}
}
