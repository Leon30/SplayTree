package test;

import java.util.Comparator;

import dinamics.SplayTree;

public class Test {

	public static void main(String[] args) {
		SplayTree<Integer> st = new SplayTree<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer intA, Integer intB) {
				return intA.compareTo(intB);
			}
		});
		
		st.add(3);
		st.add(7);
		st.add(5);
		st.add(8);
		st.add(2);
	}
}
