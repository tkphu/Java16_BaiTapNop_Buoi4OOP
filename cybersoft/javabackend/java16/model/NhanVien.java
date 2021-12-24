package cybersoft.javabackend.java16.model;

import java.util.Scanner;

public class NhanVien extends NhanSu {
	
	/* constant */
	
	final int LUONG_MOT_NGAY = 100;
	
	/* properties */
	
	private TruongPhong truongPhongQuanLy;
	
	/* constructor */
	
	public NhanVien() {
		super();
	}
	
	public NhanVien(String maSo, String hoTen, String soDienThoai, float soNgayLam) {
		super(maSo, hoTen, soDienThoai, soNgayLam);	
	}
	
	/* getter, setter */
	
	public TruongPhong getTruongPhongQuanLy() {
		return truongPhongQuanLy;
	}
	
	public void setTruongPhongQuanLy(TruongPhong truongPhongQuanLy) {
		this.truongPhongQuanLy = truongPhongQuanLy;
	}
	
	/* input, output */
	
	@Override
	public void xuatThongTin() {
		super.xuatThongTin();	
		if (truongPhongQuanLy == null) {
			System.out.print(formatLongTextTextCell("Chưa phân bố"));
		}else {
			System.out.print(formatLongTextTextCell(this.truongPhongQuanLy.getHoTen()));	
		}
		System.out.print(formatNumCell(0));
		System.out.println(formatNumCell(0));
	}

	@Override
	public void nhapThongTin(Scanner scan) {
		super.nhapThongTin(scan);
		tinhLuongThang();
	}
	
	public void xuatTenVaTruongPhong() {
		System.out.println(new StringBuilder()
				.append("Tên nhân viên: ")
				.append(this.hoTen));
		if (truongPhongQuanLy == null) {
			System.out.println("Trưởng phòng quản lý: Chưa phân bố");
		}else {
			String thongTinNhanVien = new StringBuilder()
					.append("- Trưởng phòng quản lý: ")
					.append(truongPhongQuanLy.getHoTen())
					.toString();
			System.out.println(thongTinNhanVien);		
		}
	}
	
	/* methods */
	
	@Override
	public void tinhLuongThang() {
		this.luongThang = LUONG_MOT_NGAY * this.soNgayLam;
	} 
}
