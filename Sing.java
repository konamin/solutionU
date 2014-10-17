package solutionU;

public class Sing {
	long pre_sign(RuleList ruleList) {
		long min = ruleList.getLatency();
		IndexList indexList = new IndexList(ruleList.getSize());
		ruleList.print2(indexList);
		System.out.println("L(R) = " + min + "\n");
		if (ruleList.isBest()) {
			return min;
		}
		int startNum = 0;
		min = moon(ruleList, indexList, startNum, min);
		return min;
	}

	long moon(RuleList ruleList, IndexList index, int i, long min) {
		if (i < ruleList.getSize() - 1) {
			for (int n = i + 1; n < ruleList.getSize(); n++) {
				IndexList cloneIndex = index.clone();

				if (ruleList.isChangeable(cloneIndex.get(i), cloneIndex.get(n))) {
					cloneIndex.change(i, n);
				} else {
					continue;
				}

				long latency;
				if (min >= (latency = ruleList.getLatency2(cloneIndex))
						&& !ruleList.listCheck(cloneIndex)) {
					min = latency;
					ruleList.print2(cloneIndex);
					System.out.println("L(R) = " + min + "\n");
				}
				min = moon(ruleList, cloneIndex, i + 1, min);

			}
			return min;
		} else {
			return min;
		}
	}
}
