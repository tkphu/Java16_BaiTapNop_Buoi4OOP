package cybersoft.javabackend.java16.model;

import java.util.List;
import java.util.Scanner;

public class CongTy {
	/* properties */
	
	private String ten;
	private String maSoThue;
	private float doanhThuThang;
	private float loiNhuan;
	private float tongLuongCongTy;
	private Scanner scan = new Scanner(System.in);
	
	/* getter, setter */

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		while(ten == null || "".equals(ten) || ten.trim().isEmpty()) {
			System.out.print("Không thể bỏ trống tên, vui lòng nhập lại: ");
			ten = scan.nextLine();
		}
		this.ten = ten;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		while(maSoThue == null || "".equals(maSoThue) || maSoThue.trim().isEmpty()) {
			System.out.print("Không thể bỏ trống mã số thuế, vui lòng nhập lại: ");
			maSoThue = scan.nextLine();
		}
		this.maSoThue = maSoThue;
	}

	public double getDoanhThuThang() {
		return doanhThuThang;
	}

	public void setDoanhThuThang(Float doanhThuThang) {
		while (doanhThuThang < 0) {
			System.out.print("Không thể nhập doanh thu tháng nhỏ hơn 0, vui lòng nhập lại: ");
			doanhThuThang = Float.parseFloat(scan.nextLine());
		}
		this.doanhThuThang = doanhThuThang;
	}

	public float getLoiNhuan() {
		return loiNhuan;
	}

	public float getTongLuongCongTy() {
		return tongLuongCongTy;
	}

	/* input, output */
	
	public void nhapThongTin(Scanner scan) {
		System.out.println("Nhập thông tin công ty");
		System.out.print("Tên công ty: ");
		setTen(scan.nextLine());
		System.out.print("Mã số thuế: ");
		setMaSoThue(scan.nextLine());
		System.out.print("Doanh thu tháng: ");
		setDoanhThuThang(Float.parseFloat(scan.nextLine()));
	}

	public void xuatThongTin() {
		System.out.println("Thông tin công ty");
		System.out.println(new StringBuilder()
				.append("Tên công ty: ")
				.append(this.ten)
				.append("\nMã số thuế: ")
				.append(this.maSoThue)
				.append("\nDoanh thu tháng: ")
				.append(this.doanhThuThang).toString());
	}
	
	/* methods */

	public void tinhTongLuongCongTy(List<NhanSu> danhSachNhanSu) {
		float tongLuongCongTy = 0;
		for (NhanSu ns : danhSachNhanSu) {
			tongLuongCongTy += ns.getLuongThang();
		}
		this.tongLuongCongTy = tongLuongCongTy;
	}

	public void tinhLoiNhuan() {
		this.loiNhuan = (float) (this.doanhThuThang - this.tongLuongCongTy);
	}
}
