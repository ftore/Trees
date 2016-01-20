package binaryheaps;

public class MinHeap {
	private Integer[] keys;
	private int size;
	
	public MinHeap() {
		
	}
	
	public MinHeap(int[] keys) {
		size = keys.length;
		this.keys = new Integer[size + 1];
		
		for(int i = 0; i < size; i++) {
			this.keys[i + 1] = keys[i];
		}
		
		for(int k = size/2; k >= 1; k--) {
			sink(k);
		}
		
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	private void resize(int capacity) {
		if(capacity < size) return;
		
		Integer[] tmp = new Integer[capacity];
		for(int i = 1; i <= size; i++) {
			tmp[i] = keys[i];
		}
		
		keys = tmp;
	}
	
	public void add(int x) {
		if(size == keys.length - 1) {
			resize(2 * keys.length);
		}
		
		// add x, and percolate it up to maintain heap invariant
		keys[++size] = x;
		swim(size);
	}
	
	public Integer delMin() {
		if(isEmpty()) return null;
		
		swap(1, size);
		int min = keys[size--];
		sink(1);
		keys[size + 1] = null;
		if((size > 0) && (size == (keys.length - 1) / 2)) {
			resize(keys.length / 2);
		}
		return min;
	}
	
	private void swim(int k) {
		while(k > 1 && k/2 > k) {
			swap(k, k/2);
			k = k / 2;
		}
	}
	
	private void sink(int k) {
		while(2*k <= size) {
			int j = 2*k;
			if(j < size && j > j+1) {
				j++;
			}
			
			if(!(k > j)) {
				break;
			}
			swap(k, j);
			k = j;
		}
	}
	
	public void swap(int i, int j) {
		int tmp = keys[i];
		keys[i] = keys[j];
		keys[j] = tmp;
	}
	
	public static void main(String[] args) {
		int[] arr = {2, 3, 1, 4, 6, 5};
		
		MinHeap minHeap = new MinHeap(arr);
		
		for(int i = 0; i < minHeap.size(); i++) {
			System.out.println(minHeap.delMin());
		}
		
		
	}
}
