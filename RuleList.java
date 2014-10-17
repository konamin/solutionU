package solutionU;

import java.util.ArrayList;

public class RuleList {
	ArrayList<Rule> ruleList = new ArrayList<Rule>();
	int id = 0;

	/**
	 * リストにルールを加える
	 *
	 * @param line "A 10*1 7" のような形式
	 */
	public void addRule(String line) {
		ruleList.add(new Rule(line, id));
		if (ruleList.get(ruleList.size() - 1).isDefault()) {
			ruleList.remove(getSize() - 1);
		} else {
			id++;
		}
	}

	/**
	 * ルールを返す
	 *
	 * @param index
	 * @return Rule
	 */
	public Rule getRule(int index) {
		return ruleList.get(index);
	}

	/**
	 * ビット列を返す
	 *
	 * @param index
	 * @return
	 */
	public String getBit(int index) {
		return ruleList.get(index).getBit();
	}

	/**
	 * 評価型を返す
	 *
	 * @param index
	 * @return
	 */
	public String getEva(int index) {
		return ruleList.get(index).getEva();
	}

	/**
	 * 評価パケット数を返す
	 *
	 * @param index
	 * @return
	 */
	public long getEvaluatedPackets(int index) {
		return ruleList.get(index).getEvaluatedPackets();
	}

	/**
	 * 遅延ΣL(R)を返す
	 *
	 * @return
	 */
	public long getLatency() {
		long latency = 0;
		int i = 1;
		for (Rule rule : ruleList) {
			latency = latency + rule.getEvaluatedPackets() * i;
			i++;
		}
		return latency;
	}

	public long getLatency2(IndexList index) {
		long latency = 0;
		int i = 1;
		for (int n = 0; n < getSize(); n++) {
			latency = latency + getRule(index.get(n)).getEvaluatedPackets() * i;
			i++;
		}
		return latency;
	}

	/**
	 * ルールリストに含まれるルールの数を返す
	 *
	 * @return
	 */
	public int getSize() {
		return ruleList.size();
	}

	/**
	 * ルールのビット数を返す
	 *
	 * @return
	 */
	public int getBitLength() {
		return getBit(0).length();
	}

	/**
	 * ルールを入れ替える
	 *
	 * @param i
	 * @param j
	 */
	public void change(int i, int j) {
		Rule rule = ruleList.get(i);
		ruleList.set(i, ruleList.get(j));
		ruleList.set(j, rule);
	}

	/**
	 * ルールが交換可能ならば、true
	 * ルールが”従属”しているならば、true✗
	 *
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isChangeable(int i, int j) {
		if (!getEva(i).equals(getEva(j))) {
			return !isOverlappsed(i, j);
		}
		return true;
	}

	/**
	 * ビット列が”重複”しているならば、true
	 *
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOverlappsed(int i, int j) {
		int bitLength = getBitLength();
		for (int n = 0; n < bitLength; n++) {
			char i_char = getBit(i).charAt(n);
			char j_char = getBit(j).charAt(n);
			if (i_char == '*' || j_char == '*' || i_char == j_char) {
			} else {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public RuleList clone() {
		RuleList list = new RuleList();
		list.ruleList = (ArrayList<Rule>) this.ruleList.clone();
		list.id = this.id;
		return list;
	}

	public void print() {
		for (Rule rule : ruleList) {
			System.out.print(rule.getID() + ":" + rule.getBit() + " ");
		}
		System.out.print("\n");
	}

	public void print2(IndexList index) {
		for (int n = 0; n < getSize(); n++) {
			System.out.print(getRule(index.get(n)).getID() + ":"
					+ getRule(index.get(n)).getBit() + " ");
		}
		System.out.print("\n");
	}

	/**
	 * リストから順序が入れ替わっていて、従属なルールが見つかったらtrue
	 *
	 * @return
	 */
	public boolean listCheck(IndexList index) {
		for (int i = 0; i < getSize() - 1; i++) {
			for (int j = i + 1; j < getSize(); j++) {
				if (index.get(i) < index.get(j)) {
					continue;
				}
				if (!isChangeable(index.get(i), index.get(j))) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isBest() {
		for (int i = 0; i < getSize() - 1; i++) {
			if (getRule(i).getEvaluatedPackets() >= getRule(i + 1)
					.getEvaluatedPackets()) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean isBest(IndexList index) {
		for (int i = 0; i < getSize() - 1; i++) {
			if (getRule(index.get(i)).getEvaluatedPackets() >= getRule(
					index.get(i + 1)).getEvaluatedPackets()) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}
}
