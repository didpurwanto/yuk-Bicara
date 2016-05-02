package com.yukbicara.model;

public class Kosakata {
	
	private int id;
	private String nama_daerah;
	private String isi;
	private String terjemahan;
	
	public Kosakata(int id,String nama_daerah,String isi,String terjemahan){
		this.setId(id);
		this.setIsi(isi);
		this.setNama_daerah(nama_daerah);
		this.setTerjemahan(terjemahan);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama_daerah() {
		return nama_daerah;
	}
	public void setNama_daerah(String nama_daerah) {
		this.nama_daerah = nama_daerah;
	}
	public String getIsi() {
		return isi;
	}
	public void setIsi(String isi) {
		this.isi = isi;
	}
	public String getTerjemahan() {
		return terjemahan;
	}
	public void setTerjemahan(String terjemahan) {
		this.terjemahan = terjemahan;
	}
	
	

}
