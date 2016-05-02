package com.yukbicara.model;

public class Percakapan {

	private int id;
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
	public int getSuara() {
		return suara;
	}
	public void setSuara(int suara) {
		this.suara = suara;
	}


	private String nama_daerah;
	private String isi;
	private String terjemahan;
	private int suara;
	public Percakapan(int id,String nama_daerah,String isi,String terjemahan, int suara){
		this.setId(id);
		this.setIsi(isi);
		this.setNama_daerah(nama_daerah);
		this.setTerjemahan(terjemahan);
		this.setSuara(suara);
	}
}
