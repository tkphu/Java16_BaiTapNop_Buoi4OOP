package cybersoft.javabackend.java16.model;

import java.util.Scanner;

public class TruongPhong extends NhanSu {
	/* constant */
	
	final int LUONG_MOT_NGAY = 200;
	
	/* properties */
	
	private int soNhanVienDuoiQuyen = 0;

	/* constructor */
	
	public TruongPhong() {
		super();
	}
	
	public TruongPhong(String maSo, String hoTen, String soDienThoai, float soNgayLam) {
		super(maSo, hoTen, soDienThoai, soNgayLam);
	}
	
	/* getter, setter */

	public int getSoNhanVienDuoiQuyen() {
		return soNhanVienDuoiQuyen;
	}

	/* input, output */

	@Override
	public void xuatThongTin() {
		super.xuatThongTin();
		System.out.print(formatLongTextTextCell(" "));
		System.out.print(formatNumCell(this.soNhanVienDuoiQuyen));
		System.out.println(formatNumCell(0));
	}

	@Override
	public void nhapThongTin(Scanner scan) {
		super.nhapThongTin(scan);
		tinhLuongThang();
	}

	public void xuatSoNhanVienDuoiQuyen() {
		System.out.println(new StringBuilder()
				.append("Trưởng phòng: ")
				.append(this.hoTen)
				.append("\nSố nhân viên dưới quyền: ")
				.append(this.soNhanVienDuoiQuyen)
				.toString());
	}	
	
	/* methods */

	public void tangNhanVienDuoiQuyen() {
		this.soNhanVienDuoiQuyen++;
	}

	public void giamNhanVienDuoiQuyen() {
		this.soNhanVienDuoiQuyen--;
	}

	@Override
	public void tinhLuongThang() {
		this.luongThang = LUONG_MOT_NGAY * this.soNgayLam + 100 * this.soNhanVienDuoiQuyen;
	}
}
