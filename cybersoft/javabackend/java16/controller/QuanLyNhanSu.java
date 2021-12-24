package cybersoft.javabackend.java16.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import cybersoft.javabackend.java16.model.CongTy;
import cybersoft.javabackend.java16.model.GiamDoc;
import cybersoft.javabackend.java16.model.NhanSu;
import cybersoft.javabackend.java16.model.NhanVien;
import cybersoft.javabackend.java16.model.TruongPhong;

public class QuanLyNhanSu {
	/* properties */
	
	private List<NhanSu> danhSachNhanSu;
	private CongTy congTy;

	/* getter, setter */
	
	public List<NhanSu> getDanhSachNhanSu() {
		return danhSachNhanSu;
	}

	public CongTy getCongTy() {
		return congTy;
	}

	/* constructor */
	
	public QuanLyNhanSu() {
		danhSachNhanSu = new ArrayList<>();
		themDummyDataNhanSu();
		congTy = new CongTy();
		themDummyDataCongTy();
	}

	/* input, output */
	
	public void nhapThongTinCongTy(Scanner scan) {
		congTy.nhapThongTin(scan);
	}

	public void xuatThongTinCongTy() {
		if (kiemTraNullCongTy()) {
			System.out.println("Chưa có dữ liệu về công ty!");
			return;
		}
		congTy.xuatThongTin();
	}

	public void xuatDanhSach() {
		xuatHead();
		for (NhanSu ns : danhSachNhanSu) {
			ns.xuatThongTin();
		}
	}

	public void xuatDanhSachLuong() {
		for (NhanSu ns : danhSachNhanSu) {
			ns.xuatTienLuong();
		}
	}

	public void xuatTongLuong() {
		xuatHeadTienLuong();
		for (NhanSu ns : danhSachNhanSu) {
			ns.xuatTienLuong();
		}
	}

	public void xuatTongThuNhapGiamDoc() {
		for (NhanSu ns : danhSachNhanSu) {
			if (ns instanceof GiamDoc) {
				((GiamDoc) ns).xuatTongThuNhap();
			}
		}
	}

	private void xuatHead() {
		System.out.print(formatShortTextCell("Mã số"));
		System.out.print(formatLongTextTextCell("Họ tên"));
		System.out.print(formatLongTextTextCell("Số điện thoại"));
		System.out.print(formatNumCell("Số ngày làm"));
		System.out.print(formatLongTextTextCell("Trưởng phòng quản lý"));
		System.out.print(formatNumCell("Nhân viên dưới quyền"));
		System.out.println(formatNumCell("Số cổ phần ( % )"));
	}
	
	private void xuatHeadTienLuong() {
		System.out.print(formatShortTextCell("Mã số"));
		System.out.print(formatLongTextTextCell("Họ tên"));
		System.out.print(formatNumCell("Số ngày làm"));
		System.out.println(formatNumCell("Lương tháng"));
	}

	public void themDummyDataCongTy() {
		System.out.println("Đã tự thêm DummyData Công ty!");
		congTy.setTen("Kim Phú");
		congTy.setMaSoThue("012345678988");
		congTy.setDoanhThuThang(50000f);
	}

	public void themDummyDataNhanSu() {
		System.out.println("Đã tự thêm DummyData Nhân sự!");
		NhanSu nVien1 = new NhanVien("01", "Đào Bá Bình", "0156485234", 26);
		NhanSu nVien2 = new NhanVien("02", "Nguyễn Văn A", "1515555644", 24);
		NhanSu nVien3 = new NhanVien("03", "Đào Duy Long", "0156485444", 23);
		NhanSu nVien4 = new NhanVien("04", "Nguyễn Thông", "015648697", 28);
		NhanSu nVien5 = new NhanVien("05", "Hồng Phúc", "015648584", 22);
		NhanSu nVien6 = new NhanVien("06", "Lâu La", "0156485223", 23);
		NhanSu nVien7 = new NhanVien("07", "Nguyễn Hải Nam", "015648114", 22);
		NhanSu nVien8 = new NhanVien("08", "Võ Đức Nam", "015648358", 26);

		NhanSu trPhong1 = new TruongPhong("TP01", "Trần Hào Phong", "0156485957", 25);
		NhanSu trPhong2 = new TruongPhong("TP02", "Lê Quang Khải", "0156485947", 25);

		NhanSu giamDoc1 = new GiamDoc("GD01", "Trần Kim Phú", "0158888888", 25, 70);

		danhSachNhanSu.add(nVien1);
		danhSachNhanSu.add(nVien2);
		danhSachNhanSu.add(nVien3);
		danhSachNhanSu.add(nVien4);
		danhSachNhanSu.add(nVien5);
		danhSachNhanSu.add(nVien6);
		danhSachNhanSu.add(nVien7);
		danhSachNhanSu.add(nVien8);
		danhSachNhanSu.add(trPhong1);
		danhSachNhanSu.add(trPhong2);
		danhSachNhanSu.add(giamDoc1);

		tinhLuongThang();
	}

	public boolean themNhanSu(NhanSu ns) {
		// validate - black list //
		if (ns.kiemTraNull(ns) || kiemTraMaSoTrungLap(ns) || kiemTraTongSoCoPhanCongTy(ns)) {
			System.out.println("Thêm nhân sự không thành công.");
			return false;
		}
		System.out.println("Thêm nhân sự thành công.");
		return danhSachNhanSu.add(ns);
	}
	
	public boolean xoaNhanSu(String maSo) {
		for (NhanSu ns : danhSachNhanSu) {
			// Tìm nhân viên theo mã số đã nhập
			if (ns.getMaSo().equalsIgnoreCase(maSo)) {
				// nếu xóa nhân viên thì giảm nhân viên của trưởng phòng quản lý
				if (ns instanceof NhanVien && ((NhanVien) ns).getTruongPhongQuanLy() != null) {
					((NhanVien) ns).getTruongPhongQuanLy().giamNhanVienDuoiQuyen();
					return danhSachNhanSu.remove(ns);
				}
				// Nếu xóa trưởng phòng thì bỏ tất cả các nhân viên đc phân bổ bởi trưởng phòng
				// này.
				if (ns instanceof TruongPhong) {
					for (NhanSu ns1 : danhSachNhanSu) {
						if (ns1 instanceof NhanVien && ((NhanVien) ns1).getTruongPhongQuanLy() == ns) {
							((NhanVien) ns1).setTruongPhongQuanLy(null);
						}
					}
					return danhSachNhanSu.remove(ns);
				}
			}
		}
		
		return false;
	}
	
	/* methods */

	private boolean kiemTraMaSoTrungLap(NhanSu ns) {
		for (NhanSu ns1 : danhSachNhanSu) {
			if (ns.getMaSo().equalsIgnoreCase(ns1.getMaSo())) {
				System.out.println("Không thể thêm vì mã số bị trùng lập!");
				return true;
			}
		}
		return false;
	}

	public void phanBoNhanVienVaoTruongPhong(Scanner scan) {
		if (kiemTraTruongPhong()) {
			boolean tiepTucPhanBo = true;
			for (NhanSu ns : danhSachNhanSu) {
				if (ns instanceof NhanVien) {
					((NhanVien) ns).xuatTenVaTruongPhong();
					System.out.print("Nhập 1 để phân bổ, 2 để bỏ qua, 0 để ngừng phân bổ:");
					int option = Integer.parseInt(scan.nextLine());
					while (option < 0 || option >2) {
						System.out.print("Nhập sai, hãy nhập lại: ");
						option =Integer.parseInt(scan.nextLine());
					}
					tiepTucPhanBo = xuLyLuaChonPhanBo(scan, option, ns);
				}
				if (!tiepTucPhanBo) {
					break;
				}
			}
		} else {
			System.out.println("Công ty chưa có trưởng phòng để phân bổ!");
		}
	}

	private boolean xuLyLuaChonPhanBo(Scanner scan, int option, NhanSu ns) {
		switch (option) {
		case 1: // Phân bổ
			boolean nhapMaSoDung = false;
			while (!nhapMaSoDung) {
				inDanhSachTruongPhong(); // in danh sach trưởng phòng
				System.out.println("Nhập mã số của trưởng phòng muốn phân bổ: ");
				String input = scan.nextLine(); // lấy mã nhập vào
				TruongPhong trPhong = new TruongPhong();
				trPhong = layTruongPhongMuonPhanBo(input); // lấy trưởng phòng theo mã nhập vào
				if (trPhong == null) { // Nếu mã nhập sai
					System.out.println("Mã vừa nhập không phải là trưởng phòng.");
				} else { // Phân bố trưởng phòng
					nhapMaSoDung = true;
					trPhong.tangNhanVienDuoiQuyen();
					((NhanVien) ns).setTruongPhongQuanLy(trPhong);
					System.out.println("Phân bổ thành công");
				}	
			}
			return true;
		case 2: // bỏ qua nhân viên này
			return true;
		case 0: // ngừng phân bổ
			return false;
		default:
			return false;
			}	
	}

	private void inDanhSachTruongPhong() {
		for (NhanSu truongPhong : danhSachNhanSu) {
			if (truongPhong instanceof TruongPhong) {
				System.out.println(new StringBuilder()
						.append("Trưởng phòng: ")
						.append(truongPhong.getHoTen())
						.append(" Mã số: ")
						.append(truongPhong.getMaSo()).toString());
			}
		}
	}

	private boolean kiemTraTruongPhong() { // kiểm tra xem có trưởng phòng không
		for (NhanSu ns : danhSachNhanSu) {
			if (ns instanceof TruongPhong) {
				return true;
			}
		}
		return false;
	}

	private TruongPhong layTruongPhongMuonPhanBo(String input) {
		for (NhanSu ns : danhSachNhanSu) { // So mã số vừa nhập để tìm Trưởng Phòng
			if (input.equalsIgnoreCase(ns.getMaSo())) { // nếu mã số nhập vào có trong danh sách
				if (!(ns instanceof TruongPhong)) { // nếu nhân viên này không phải là trưởng phòng
					System.out.println("Mã vừa nhập không phải của trưởng phòng");
					break;
				} else {
					return (TruongPhong) ns;
				}
			}
		}
		return null;
	}

	public void tinhLuongThang() {
		for (NhanSu ns : danhSachNhanSu) {
			ns.tinhLuongThang();
		}
	}

	public void timNhanVienCoLuongCaoNhat() {
		NhanSu nhanVienCoLuongCaoNhat = new NhanVien();
		boolean coNhanVien = false;
		// Tìm nhân viên có lương cao nhất
		for (NhanSu ns : danhSachNhanSu) {
			if (ns instanceof NhanVien) {
				if (nhanVienCoLuongCaoNhat.getLuongThang() < ns.getLuongThang()) {
					nhanVienCoLuongCaoNhat = ns;
				}
				coNhanVien = true;
			}
		}
		// nếu có nhân viên
		if (coNhanVien) {
			System.out.println("Nhân viên có lương cao nhất:");
			// Tìm nhân viên có lương cao nhất bằng nhau.
			xuatHeadTienLuong();
			for (NhanSu ns : danhSachNhanSu) {
				if (nhanVienCoLuongCaoNhat.getLuongThang() == ns.getLuongThang()) {
					ns.xuatTienLuong();
				}
			}
		} else {
			System.out.println("Công ty chưa có nhân viên!");
		}
	}

	public void timTruongPhongCoSoLuongNhanVienNhieuNhat() {
		NhanSu truongPhongCoNhanVienNhieuNhat = new TruongPhong();
		boolean coTruongPhong = false;
		// Tìm trưởng phòng có nhân viên dưới quyền nhiều nhất.
		for (NhanSu ns : danhSachNhanSu) {
			if (ns instanceof TruongPhong) {
				if (((TruongPhong) truongPhongCoNhanVienNhieuNhat).getSoNhanVienDuoiQuyen() < ((TruongPhong) ns)
						.getSoNhanVienDuoiQuyen()) {
					truongPhongCoNhanVienNhieuNhat = ns;
				}
				coTruongPhong = true;
			}
		}
		// nếu có trưởng phòng
		if (coTruongPhong) {
			// Nếu chưa có trưởng phòng nào có nhân viên dưới quyền
			if (((TruongPhong) truongPhongCoNhanVienNhieuNhat).getSoNhanVienDuoiQuyen() == 0) {
				System.out.println("Các trưởng phòng chưa có nhân viên dưới quyền!");
			} else {
				// Tìm trưởng phòng có nhân viên dưới quyền nhiều nhất bằng nhau.
				System.out.println("Trưởng phòng có nhân viên dưới quyền nhiều nhất:");
				for (NhanSu ns : danhSachNhanSu) {
					if (ns instanceof TruongPhong) {
						if (((TruongPhong) truongPhongCoNhanVienNhieuNhat)
								.getSoNhanVienDuoiQuyen() == ((TruongPhong) ns).getSoNhanVienDuoiQuyen()) {
							((TruongPhong) ns).xuatSoNhanVienDuoiQuyen();
						}
					}
				}
			}
		} else {
			System.out.println("Công ty chưa có trưởng phòng!");
		}
	}

	public void sapXepTenTheoThuTuABC() {
		for (int i = 0; i < danhSachNhanSu.size() - 1; i++) {
			for (int j = i + 1; j < danhSachNhanSu.size(); j++) {
				String[] nameI = danhSachNhanSu.get(i).getHoTen().split(" ");
				String lastNameI = nameI[nameI.length - 1];
				String[] nameJ = danhSachNhanSu.get(j).getHoTen().split(" ");
				String lastNameJ = nameJ[nameJ.length - 1];
				if (lastNameJ.compareToIgnoreCase(lastNameI) < 0) {
					Collections.swap(danhSachNhanSu, i, j);
				}
			}
		}
	}

	public void sapXepLuongGiamDan() {
		for (int i = 0; i < danhSachNhanSu.size() - 1; i++) {
			for (int j = i + 1; j < danhSachNhanSu.size(); j++) {
				if (danhSachNhanSu.get(i).getLuongThang() < danhSachNhanSu.get(j).getLuongThang()) {
					Collections.swap(danhSachNhanSu, i, j);
				}
			}
		}
	}

	public void timGiamDocCoPhanNhieuNhat() {
		NhanSu giamDocCoPhanNhieuNhat = new GiamDoc();
		boolean coGiamDoc = false;
		// Tìm giám đốc có số cổ phần nhiều nhất
		for (NhanSu ns : danhSachNhanSu) {
			if (ns instanceof GiamDoc) {
				if (((GiamDoc) giamDocCoPhanNhieuNhat).getSoCoPhan() < ((GiamDoc) ns).getSoCoPhan()) {
					giamDocCoPhanNhieuNhat = ns;
				}
				coGiamDoc = true;
			}
		}
		// nếu có giám đốc
		if (coGiamDoc) {
			// Tìm giám đốc có số cổ phần nhiều nhất bằng nhau.
			System.out.println("Giám đốc có số cổ phần nhiều nhất:");
			for (NhanSu ns : danhSachNhanSu) {
				if (ns instanceof GiamDoc) {
					if (((GiamDoc) giamDocCoPhanNhieuNhat).getSoCoPhan() == ((GiamDoc) ns).getSoCoPhan()) {
						((GiamDoc) ns).xuatSoCoPhan();
					}
				}
			}
		} else {
			System.out.println("Công ty chưa có giám đốc!");
		}
	}

	public void tinhTongThuNhapGiamDoc() {
		// thu nhap = luong thang + so co phan * loi nhuan cong ty
		for (NhanSu ns : danhSachNhanSu) {
			if (ns instanceof GiamDoc) {
				((GiamDoc) ns).tinhTongThuNhap(congTy.getLoiNhuan());
			}
		}
	}

	public void tinhLoiNhuanCongTy() {
		congTy.tinhLoiNhuan();
		System.out.println(new StringBuilder()
				.append("Lợi nhuận công ty: ")
				.append(congTy.getLoiNhuan()));
	}

	public void tinhTongLuongCongTy() {
		congTy.tinhTongLuongCongTy(danhSachNhanSu);
		System.out.println(new StringBuilder()
				.append("Tổng lương công ty: ")
				.append(congTy.getTongLuongCongTy()));
	}

	private boolean kiemTraNullCongTy() {
		if (congTy.getTen() == null || "".equals(congTy.getTen()) || congTy.getMaSoThue() == null
				|| "".equals(congTy.getMaSoThue())) {
			return true;
		}
		return false;
	}
	
	private boolean kiemTraTongSoCoPhanCongTy(NhanSu ns) {
		if (ns instanceof GiamDoc) {			
			float soCoPhanCongTy = tinhSoCoPhanTrongCongTy();
			soCoPhanCongTy += ((GiamDoc) ns).getSoCoPhan();
			if (soCoPhanCongTy > 100) {
				System.out.println("Tổng số cổ phần vượt quá 100%!");
				return true;
			}
		}
		return false;
	}

	private float tinhSoCoPhanTrongCongTy() {
		float soCoPhanCongTy =0;
		for (NhanSu ns : danhSachNhanSu) {
			if (ns instanceof GiamDoc) {
				soCoPhanCongTy += ((GiamDoc) ns).getSoCoPhan();
			}
		}
		return soCoPhanCongTy;
	}
	
	private String formatShortTextCell(String text) {
		String paddLeft = "%3s";
		String paddRight = "%-6s";
		return String.format(paddLeft, " ") + String.format(paddRight, text) + "|";
	}

	private String formatNumCell(String num) {
		String paddLeft = "%2s";
		String paddRight = "%-23s";
		return String.format(paddLeft, " ") + String.format(paddRight, num) + "|";
	}
	
	private String formatLongTextTextCell(String text) {
		String paddLeft = "%-25s";
		String paddRight = "%-1s";
		return String.format(paddLeft, " " + text) + String.format(paddRight, "|");
	}	
}
