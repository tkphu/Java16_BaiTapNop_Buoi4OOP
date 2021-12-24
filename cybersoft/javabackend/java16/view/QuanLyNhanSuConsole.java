package cybersoft.javabackend.java16.view;

import java.util.Scanner;

import cybersoft.javabackend.java16.controller.QuanLyNhanSu;
import cybersoft.javabackend.java16.model.GiamDoc;
import cybersoft.javabackend.java16.model.NhanSu;
import cybersoft.javabackend.java16.model.NhanVien;
import cybersoft.javabackend.java16.model.TruongPhong;

public class QuanLyNhanSuConsole {
	/* properties */
	
	private QuanLyNhanSu controller;
	private Scanner scan = new Scanner(System.in);
	
	/* constructors */
	
	public QuanLyNhanSuConsole() {
		controller = new QuanLyNhanSu();
	}
	
	/* method */
	
	public void start(Scanner scan) {
		int option;	
		do {
			inMeNu();
			System.out.print("Mời nhập: ");
			option = Integer.parseInt(scan.nextLine());
		}while(xuLyMeNu(option));
	}
	
	public void inMeNu() {
		System.out.println("*******Danh sách chức năng*********");
		System.out.println("0. Thoát chương trình.");
		System.out.println("1. Nhập thông tin công ty.");
		System.out.println("2. In thông tin công ty.");
		System.out.println("3. Thêm thông tin một nhân sự.");
		System.out.println("4. Xóa thông tin một nhân sự.");
		System.out.println("5. Xuất ra thông tin toàn bộ người trong công ty.");
		System.out.println("6. Phân bổ nhân viên vào trưởng phòng.");
		System.out.println("7. Tính và xuất tổng lương cho toàn công ty.");
		System.out.println("8. Tìm nhân viên thường có lương cao nhất.");
		System.out.println("9. Tìm Trưởng phòng có số lượng nhân viên dưới quyền nhiều nhất.");
		System.out.println("10. Sắp xếp nhân viên toàn công ty theo thứ tự abc.");
		System.out.println("11. Sắp xếp nhân viên toàn công ty theo thứ tự lương giảm dần.");
		System.out.println("12. Tìm Giám đốc có số lượng cổ phần nhiều nhất.");
		System.out.println("13. Tính và xuất tổng thu nhập của từng Giám đốc.");
		System.out.println("***********************************");
	}

	public boolean xuLyMeNu(int option) {
		boolean isContinue = true;
		switch (option) {
		case 1: // Nhập thông tin công ty
			controller.nhapThongTinCongTy(scan);
			break;
		case 2: // Xuất thông tin công ty
			controller.xuatThongTinCongTy();
			break;
		case 3: // thêm nhân sự
			themNhanSu();
			break;
		case 4: // xóa nhân sự
			xoaNhanSu();
			break;
		case 5: // in thông tin tất cả nhân viên trong công ty
			controller.xuatDanhSach();
			break;
		case 6: // Phân bổ nhân viên vào trưởng phòng
			controller.phanBoNhanVienVaoTruongPhong(scan);
			break;
		case 7: // Tính và xuất lương cho toàn bộ công ty
			controller.tinhLuongThang();
			controller.xuatTongLuong();
			break;
		case 8: // Tìm nhân viên có lương cao nhất
			controller.timNhanVienCoLuongCaoNhat();
			break;
		case 9: // Tìm trưởng phòng có số lượng nhân viên dưới quyền nhiều nhất
			controller.timTruongPhongCoSoLuongNhanVienNhieuNhat();
			break;
		case 10: // Sắp xếp nhân viên toàn công ty theo thứ tự abc
			controller.sapXepTenTheoThuTuABC();
			controller.xuatDanhSach();
			break;
		case 11: // Sắp xếp nhân viên toàn công ty lương giảm dần
			controller.sapXepLuongGiamDan();
			controller.xuatDanhSachLuong();
			break;
		case 12: // Tìm giám đốc có số lượng cổ phần nhiều nhất.
			controller.timGiamDocCoPhanNhieuNhat();
			break;
		case 13: // Tính và xuất tổng thu nhập của từng giám đốc
			controller.tinhTongLuongCongTy();
			controller.tinhLoiNhuanCongTy();
			controller.tinhTongThuNhapGiamDoc();
			controller.xuatTongThuNhapGiamDoc();
			break;
		case 0:
			isContinue = false;
			break;
		default:
			System.out.println("Vui lòng nhập lựa chọn hợp lệ");
			break;
		}
		return isContinue;
	}

	private void xoaNhanSu() {
		System.out.print("Nhập mã nhân viên muốn xóa: ");
		String maSo = scan.nextLine();
		if(controller.xoaNhanSu(maSo)) {
			System.out.println("Xóa thành công!");
		}else{
			System.out.println("Sai mã, xóa thất bại. ");
		}
	}

	private void themNhanSu() {
		System.out.println("Loại nhân viên: ");
		System.out.println("1. Nhân viên");
		System.out.println("2. Trưởng phòng");
		System.out.println("3. Giám đốc");
		System.out.print("Lựa chọn: ");
		NhanSu nhanSu;
		switch (Integer.parseInt(scan.nextLine())) {
		case 1:
			nhanSu = new NhanVien();
			break;
		case 2:
			nhanSu = new TruongPhong();
			break;
		case 3:
			nhanSu = new GiamDoc();
			break;
		default:
			System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại sau.");
			return;
		}
		nhanSu.nhapThongTin(scan);
		controller.themNhanSu(nhanSu);
	}
}	
