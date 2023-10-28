import java.util.ArrayList;

public class ArithmetikLeser {

	private ArrayList<String> ausdruck = new ArrayList<String>();
	private String[] einstellig = { "'Klammer auf'", "'Klammer zu'", "mal", "plus", "komma", "minus", "punkt", "durch",
			"eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun" };
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

		for (String s : teil) {

			einstellig[8] = "ein";

			for (int i = 0; i < s.length(); i++) {
				stelle = s.length() - i;

				if (s.charAt(i) != '0') {

					if (stelle == 1) {

						einstellig[8] = "eins";

						stb.append(einstellig[(s.charAt(i) - 41)] + " ");
					}

					else if (stelle == 3 || stelle == 4 || stelle == 6 || stelle == 7 || stelle == 9) {
						stb.append(einstellig[s.charAt(i) - 41]);
					}

					else if (stelle == 2 || stelle == 5 || stelle == 8) {

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
							stb.append(einstellig[s.charAt(i + 1) - 41] + zehner[s.charAt(i) - 49]);
						}

						else {
							stb.append(einstellig[s.charAt(i + 1) - 41] + "und" + zehner[s.charAt(i) - 49]);
						}
						i++;
					}

					if (stelle == 3 || stelle == 6 || stelle == 9)
						stb.append("hundert");

					if (stelle == 4)
						stb.append("tausend");

					if (stelle == 7)
						if (s.charAt(i) == 49 && s.length() == 7)
							stb.append("e Million ");
						else
							stb.append(" Millionen ");

				}
			}
			stb.append(" ");
		}

		return stb.toString();

	}
}