package cybersoft.javabackend.java16.model;

import java.util.Scanner;

public class NhanSu {
	
	/* properties */
	
	protected String maSo;
	protected String hoTen;
	protected String soDienThoai;
	protected float soNgayLam;
	protected float luongThang;
	private Scanner scan = new Scanner(System.in);

	/* constructor */
	
	public NhanSu() {

	}

	public NhanSu(String maSo, String hoTen, String soDienThoai, float soNgayLam) {
		setMaSo(maSo);
		setHoTen(hoTen);
		setSoDienThoai(soDienThoai);
		setSoNgayLam(soNgayLam);
	}

	/* getter, setter */
	
	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		while (hoTen == null || "".equals(hoTen) || hoTen.trim().isEmpty()) {
			System.out.print("Không thể bỏ trống tên, vui lòng nhập lại: ");
			hoTen = scan.nextLine();
		}
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		while (soDienThoai.matches(".*[a-zA-Z].*")) {
			System.out.print("Số điện thoại không đúng,vui lòng nhập lại: ");
			soDienThoai = scan.nextLine();
		}
		this.soDienThoai = soDienThoai;
	}

	public float getSoNgayLam() {
		return soNgayLam;
	}

	public void setSoNgayLam(float soNgayLam) {
		while (soNgayLam < 0 || soNgayLam > 31) {
			System.out.print("Số ngày làm không hợp lệ, vui lòng nhập lại ( số ngày làm trong 1 tháng ): ");
			soNgayLam = Float.parseFloat(scan.nextLine());
		}
		this.soNgayLam = soNgayLam;
	}

	public float getLuongThang() {
		return luongThang;
	}

	public String getMaSo() {
		return maSo;
	}

	public void setMaSo(String maSo) {
		while (maSo == null || "".equals(maSo) || maSo.trim().isEmpty()) {
			System.out.print("Không thể bỏ trống mã số,vui lòng nhập lại: ");
			maSo = scan.nextLine();
		}
		this.maSo = maSo;
	}

	/* input, output */
	
	public void xuatThongTin() {
		System.out.print(formatShortTextCell(this.maSo)); 
		System.out.print(formatLongTextTextCell(this.hoTen));
		System.out.print(formatLongTextTextCell(this.soDienThoai));
		System.out.print(formatNumCell(this.soNgayLam));
		// truongphongquanly
		// sonhanvienduoiquyen
		// socophan	
	}

	public void nhapThongTin(Scanner scan) {
		System.out.println("Nhập thông tin");
		System.out.print("Mã số: ");
		setMaSo(scan.nextLine());
		System.out.print("Họ tên: ");
		setHoTen(scan.nextLine());
		System.out.print("Số điện thoại: ");
		setSoDienThoai(scan.nextLine());
		System.out.print("Số ngày làm: ");
		setSoNgayLam(Float.parseFloat(scan.nextLine()));
	}

	/* methods */
	
	public void tinhLuongThang() {
		this.luongThang = 0;
	}

	public boolean kiemTraNull(NhanSu ns) {
		if (hoTen == null || "".equals(hoTen) || hoTen.trim().isEmpty()) {
			System.out.println("Họ và tên không được để trống.");
			return true;
		}
		if (soDienThoai.matches(".*[a-zA-Z].*")) {
			System.out.println("Số điện thoại sai.");
			return true;
		}
		if (soNgayLam < 0 || soNgayLam > 31) {
			System.out.println("Số ngày làm không hợp lệ ( số ngày làm trong tháng )");
			return true;
		}
		if (maSo == null || "".equals(maSo) || maSo.trim().isEmpty()) {
			System.out.println("Mã số không được để trống");
			return true;
		}
		return false;
	}

	protected String formatShortTextCell(String text) {
		String paddLeft = "%3s";
		String paddRight = "%-6s";
		return String.format(paddLeft, " ") + String.format(paddRight, text) + "|";
	}

	protected String formatNumCell(Number num) {
		String paddLeft = "%2s";
		String paddRight = "%-23s";
		return String.format(paddLeft, " ") + String.format(paddRight, num) + "|";
	}
	
	protected String formatLongTextTextCell(String text) {
		String paddLeft = "%-25s";
		String paddRight = "%-1s";
		return String.format(paddLeft, " " + text) + String.format(paddRight, "|");
	}

	public void xuatTienLuong() {
		System.out.print(formatShortTextCell(this.maSo)); 
		System.out.print(formatLongTextTextCell(this.hoTen));
		System.out.print(formatNumCell(this.soNgayLam));
		System.out.println(formatNumCell(this.luongThang));
	}
}
