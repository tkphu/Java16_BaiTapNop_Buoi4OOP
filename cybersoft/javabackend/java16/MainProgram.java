package cybersoft.javabackend.java16;

import java.util.Scanner;

import cybersoft.javabackend.java16.view.QuanLyNhanSuConsole;

public class MainProgram {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		QuanLyNhanSuConsole program = new QuanLyNhanSuConsole();
		System.out.println("===============Chương Trình Quản Lý Nhân Sự - Java 16===============");
		program.start(scan);
		System.out.println("Chương trình đã kết thúc.");
	}

}
