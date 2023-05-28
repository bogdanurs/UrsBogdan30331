package LabSession6.src;

public class Main {

	public static void main(String[] args) {
		Controller c = new Controller();
		Robot r = new Robot ();
		c.r = r;
		r.c = c;
		c.start();
		r.start();
	}
}
