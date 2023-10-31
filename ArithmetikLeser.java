import java.util.ArrayList;

public class ArithmetikLeser {

	private ArrayList<String> ausdruck = new ArrayList<String>();
	private String[] einer = { "'Klammer auf'", "'Klammer zu'", "mal", "plus", "Komma", "minus", "Punkt", "durch",
			"null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun" };
	private String[] zehner = { "zehn", "zwanzig", "dreißig", "vierzig", "fünfzig", "sechzig", "siebzig", "achtzig",
	"neunzig" };
	private String[] mill = { "M", "B", "Tr", "Quadr", "Quint", "Sext", "Sept", "Okt", "Non", "Dez", "Undez", "Dodez",
			"Tredez", "Quattuordez", "Quindez", "Sedez", "Septendez", "Dodevigint", "Undevigint", "Vigint", "Unvigint",
			"Dovigint", "Tresvigint", "Quattuorvigint", "Quinvigint", "Sevigint", "Septenvigint", "Dodetrigint",
			"Undetrigint", "Trigint", "Untrigint", "Dotrigint", "Tretrigint", "Quattuortrigint", "Quintrigint",
			"Setrigint", "Septentrigint", "Oktotrigint", "Novemtrigint", "Quadragint", "Unquadragint", "Doquadragint",
			"Trequadragint", "Quattuorquadragint", "Quinquadragint", "Sequadragint", "Septenquadragint",
			"Oktoquadragint", "Novemquadragint", "Quinquagint" };

	// Ausdruck muss einzelne Teile mit Leerzeichen trennen, inkl. Klammern und
	// Komma 

	// Zahlen bis exkl. 10 ^ 306 auslesbar

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

				if (stelle == 1 || stelle >= 7 && ((stelle - 1) % 3 == 0 && s.length() != stelle)) {
					einer[9] = "eins";
				} else {
					einer[9] = "ein";
				}

				if (s.charAt(i) != '0') {

					if ((stelle + 1) % 3 == 0) {

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
					} else {
						stb.append(einer[s.charAt(i) - 40]);
					}
					ta = true;
				}

				if (s.charAt(i) != '0' && stelle % 3 == 0) {
					stb.append("hundert");
				}

				if (ta == true) {

					if (stelle == 4) {
						stb.append("tausend");
						ta = false;

					} else if (stelle >= 7 && (stelle - 7) % 6 == 0) {
						if (s.charAt(i) == '1' && s.length() == stelle) {
							stb.append("e " + mill[(stelle - 7) / 6] + "illion ");
						} else {
							stb.append(" " + mill[(stelle - 7) / 6] + "illionen ");
						}
						ta = false;

					} else if (stelle >= 10 && (stelle - 10) % 6 == 0) {
						if (s.charAt(i) == '1' && s.length() == stelle) {
							stb.append("e " + mill[(stelle - 10) / 6] + "illiarde ");
						} else {
							stb.append(" " + mill[(stelle - 10) / 6] + "illiarden ");
						}
						ta = false;
					}
					
				}
				
			}
			if (ta == true)
			stb.append(" ");
		}
		return stb.toString();
	}
}
