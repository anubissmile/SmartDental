package com.smict.all.model;

public class ToothModel {
	private String tooth_pic_code,tooth_pic_name,surface;
	private String arch;
	private Boolean B,D,L,Li,La,M,O,P,i;
	private String type_tooth,type_surface,type_mouth,type_quadrant,type_sextant,type_arch,type_tooth_rang;
	private int tooth_id,tooth_num;
	public ToothModel(){
		
	}
	
	public int getTooth_id() {
		return tooth_id;
	}

	public void setTooth_id(int tooth_id) {
		this.tooth_id = tooth_id;
	}

	public int getTooth_num() {
		return tooth_num;
	}

	public void setTooth_num(int tooth_num) {
		this.tooth_num = tooth_num;
	}

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public Boolean getB() {
		return B;
	}

	public void setB(Boolean b) {
		B = b;
	}

	public Boolean getD() {
		return D;
	}

	public void setD(Boolean d) {
		D = d;
	}

	public Boolean getL() {
		return L;
	}

	public void setL(Boolean l) {
		L = l;
	}

	public Boolean getLi() {
		return Li;
	}

	public void setLi(Boolean li) {
		Li = li;
	}

	public Boolean getLa() {
		return La;
	}

	public void setLa(Boolean la) {
		La = la;
	}

	public Boolean getM() {
		return M;
	}

	public void setM(Boolean m) {
		M = m;
	}

	public Boolean getO() {
		return O;
	}

	public void setO(Boolean o) {
		O = o;
	}

	public Boolean getP() {
		return P;
	}

	public void setP(Boolean p) {
		P = p;
	}

	public Boolean getI() {
		return i;
	}

	public void setI(Boolean i) {
		this.i = i;
	}

	public String getTooth_pic_code() {
		return tooth_pic_code;
	}

	public void setTooth_pic_code(String tooth_pic_code) {
		this.tooth_pic_code = tooth_pic_code;
	}

	public String getTooth_pic_name() {
		return tooth_pic_name;
	}

	public void setTooth_pic_name(String tooth_pic_name) {
		this.tooth_pic_name = tooth_pic_name;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getType_tooth() {
		return type_tooth;
	}

	public void setType_tooth(String type_tooth) {
		this.type_tooth = type_tooth;
	}

	public String getType_surface() {
		return type_surface;
	}

	public void setType_surface(String type_surface) {
		this.type_surface = type_surface;
	}

	public String getType_mouth() {
		return type_mouth;
	}

	public void setType_mouth(String type_mouth) {
		this.type_mouth = type_mouth;
	}

	public String getType_quadrant() {
		return type_quadrant;
	}

	public void setType_quadrant(String type_quadrant) {
		this.type_quadrant = type_quadrant;
	}

	public String getType_sextant() {
		return type_sextant;
	}

	public void setType_sextant(String type_sextant) {
		this.type_sextant = type_sextant;
	}

	public String getType_arch() {
		return type_arch;
	}

	public void setType_arch(String type_arch) {
		this.type_arch = type_arch;
	}

	public String getType_tooth_rang() {
		return type_tooth_rang;
	}

	public void setType_tooth_rang(String type_tooth_rang) {
		this.type_tooth_rang = type_tooth_rang;
	}

}