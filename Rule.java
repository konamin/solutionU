package solutionU;

public class Rule {
	private String eva = "";
	private String bits = "";
	private long evaluatedPacketsLong = 0;
	private int id;

	public Rule(String line, int id_Number) {
		String str[] = line.split(" ");
		eva = str[0];
		bits = str[1];
		evaluatedPacketsLong = Long.parseLong(str[2]);
		id = id_Number;
	}

	public String getEva() {
		return eva;
	}

	public String getBit() {
		return bits;
	}

	public long getEvaluatedPackets() {
		return evaluatedPacketsLong;
	}

	public int getID() {
		return id;
	}

	/**
	 * デフォルトルールならば、trueを返す
	 * @return boolean
	 */
	public boolean isDefault() {
		String str = "";
		for (int i = 0; i < bits.length(); i++) {
			str += "*";
		}
		if (bits.equals(str)) {
			return true;
		} else {
			return false;
		}
	}

}
