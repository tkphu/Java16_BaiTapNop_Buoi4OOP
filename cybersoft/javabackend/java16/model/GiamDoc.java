package cybersoft.javabackend.java16.model;

import java.util.Scanner;

public class GiamDoc extends NhanSu {
	
	/* constant */
	
	final int LUONG_MOT_NGAY = 300;
	
	/* properties */
	
	private float soCoPhan;
	private float tongThuNhap;
	Scanner scan = new Scanner (System.in);
	
	/* constructor */
	
	public GiamDoc() {
		super();
	}
	public GiamDoc(String maSo, String hoTen, String soDienThoai, float soNgayLam, float soCoPhan) {
		super(maSo, hoTen, soDienThoai, soNgayLam);
		this.soCoPhan = soCoPhan;
	}
	
	/* getter, setter */
	
	public float getTongThuNhap() {
		return tongThuNhap;
	}
	public float getSoCoPhan() {
		return soCoPhan;
	}
	public void setSoCoPhan(float soCoPhan) {
		while (soCoPhan > 100|| soCoPhan < 0) {
			System.out.print("Số cổ phần không thể quá 100% hoặc âm, xin nhập lại: ");
			soCoPhan = Float.parseFloat(scan.nextLine());
		}
		this.soCoPhan = soCoPhan;
	}
	
	/* input, output */
	
	@Override
	public void nhapThongTin(Scanner scan) {
		super.nhapThongTin(scan);
		System.out.print("Số cổ phần: ");
		this.soCoPhan = Float.parseFloat(scan.nextLine());
		tinhLuongThang();
	}
	
	@Override
	public void xuatThongTin() {
		super.xuatThongTin();
		System.out.print(formatLongTextTextCell(" "));
		System.out.print(formatNumCell(0));
		System.out.println(formatNumCell(this.soCoPhan));
	}
	
	public void xuatSoCoPhan() {
		System.out.println(new StringBuilder()
				.append("Giám đốc: ")
				.append(this.hoTen)
				.append("\nSố cổ phần ( % ): ")
				.append(this.soCoPhan));
	}
	
	public void xuatTongThuNhap() {
		System.out.println(new StringBuilder()
				.append("Giám đốc: ")
				.append(this.hoTen)
				.append("\nTổng thu nhập: ")
				.append(this.tongThuNhap));
	}
	/* methods */
	
	@Override	
	public void tinhLuongThang() {
		this.luongThang = LUONG_MOT_NGAY * this.soNgayLam;
	}
	
	public void tinhTongThuNhap(float loiNhuan) {
		this.tongThuNhap = this.luongThang + (this.soCoPhan/100) * loiNhuan;
	}
	
	@Override
	public boolean kiemTraNull(NhanSu ns) {
		if (soCoPhan > 100 || soCoPhan < 0) {
			return false;
		}
		return super.kiemTraNull(ns);
	}
}
