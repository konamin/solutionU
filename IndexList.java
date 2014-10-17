package solutionU;

public class IndexList {
	int[] index;
	int size = 0;

	IndexList(int n) {
		index = new int[n];
		size = n;
		for (int i = 0; i < n; i++) {
			index[i] = i;
		}
	}

	private IndexList() {
	}

	public int get(int i) {
		return index[i];
	}

	public void change(int i, int j) {
		int temp = index[i];
		index[i] = index[j];
		index[j] = temp;
	}

	public IndexList clone() {
		IndexList list = new IndexList();
		list.index = index.clone();
		return list;
	}

	public void print() {
		for (int i = 0; i < index.length; i++) {
			System.out.print(index[i] + ", ");
		}
		System.out.println();
	}
}
