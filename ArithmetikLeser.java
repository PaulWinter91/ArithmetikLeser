import java.util.ArrayList;

public class ArithmetikLeser {

	private ArrayList<String> ausdruck = new ArrayList<String>();
	private String[] einer = { "'Klammer auf'", "'Klammer zu'", "mal", "plus", "komma", "minus", "punkt", "durch",
			"null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun" };
	private String[] zehner = { "zehn", "zwanzig", "dreißig", "vierzig", "fünfzig", "sechzig", "siebzig", "achtzig",
	"neunzig" };

	// Ausdruck muss einzelne Teile mit Leerzeichen trennen, inkl. Klammern
	public ArithmetikLeser(String ausdruck) {
		this.ausdruck.add(ausdruck);
	}

	public String readTerm() {

		String[] teil = ausdruck.get(0).split(" ");

		StringBuilder stb = new StringBuilder();
		int stelle = 0;
		boolean ta = false;

		for (String s : teil) {

			for (int i = 0; i < s.length(); i++) {
				stelle = s.length() - i;

				if (stelle == 1 || (stelle == 7 && s.length() != 7) || (stelle == 10 && s.length() != 10)) {
					einer[9] = "eins";
				} else {
					einer[9] = "ein";
				}

				if (s.charAt(i) != '0') {

					if (stelle == 1 || stelle == 3 || stelle == 4 || stelle == 6 || stelle == 7 || stelle == 9
							|| stelle == 10 || stelle == 12) {
						stb.append(einer[s.charAt(i) - 40]);
					}

					else if (stelle == 2 || stelle == 5 || stelle == 8 || stelle == 11) {

						if (s.charAt(i + 1) == '0') {
							stb.append(zehner[s.charAt(i) - 49]);
						}

						else if (s.charAt(i) == '1' && s.charAt(i + 1) == '1') {
							stb.append("elf");
						}

						else if (s.charAt(i) == '1' && s.charAt(i + 1) == '2') {
							stb.append("zwölf");
						}

						else if (s.charAt(i) == '1' && s.charAt(i + 1) == '6') {
							stb.append("sechzehn");
						}

						else if (s.charAt(i) == '1' && s.charAt(i + 1) == '7') {
							stb.append("siebzehn");
						}

						else if (s.charAt(i) == '1') {
							stb.append(einer[s.charAt(i + 1) - 40] + zehner[s.charAt(i) - 49]);
						}

						else {
							stb.append(einer[s.charAt(i + 1) - 40] + "und" + zehner[s.charAt(i) - 49]);
						}
						i++;
						stelle = s.length() - i;
					}
					ta = true;
				}

				if (s.charAt(i) != '0' && (stelle == 3 || stelle == 6 || stelle == 9 || stelle == 12)) {
					stb.append("hundert");
				}

				if (ta == true) {

					if (stelle == 4) {
						stb.append("tausend");

					} else if (stelle == 7) {
						if (s.charAt(i) == '1' && s.length() == 7) {
							stb.append("e Million ");
						} else {
							stb.append(" Millionen ");
						}

					} else if (stelle == 10) {
						if (s.charAt(i) == '1' && s.length() == 10) {
							stb.append("e Milliarde ");
						} else {
							stb.append(" Milliarden ");
						}
					}

					ta = false;
				}

			}

			stb.append(" ");

		}

		return stb.toString();

	}
}
