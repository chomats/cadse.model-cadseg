package fr.imag.adele.cadse.cadseg.exp;

import java.io.StringReader;

public class TestExp extends ExpressionParse {

	public TestExp(String exp) {
		super(new StringReader(exp));
	}

	@Override
	protected void parseBEGIN() {
	}

	@Override
	protected void parseEND(ExpToken e) {
	}

	public static void main(String[] args) throws ParseException {
		testExp("${parent.name}");
		testExp("{4545}${parent.parent.name[sdd={+1}]}");
	}

	private static void testExp(String exp) throws ParseException {
		TestExp t = new TestExp(exp);
		t.main();
	}
}
