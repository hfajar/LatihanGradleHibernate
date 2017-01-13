package com.LatihanGradle.fajar.dataMaster.model.pelanggan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="data_pelanggan")
public class Pelanggan {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long idPelanggan;
	@Column(name="nama")
	private String namaPelanggan;
	@Column(name="alamat")
	private String alamatPelanggan;
	@Column(name="email")
	private String emailPelanggan;
	@Column(name="noHp")
	private String noHpPelanggan;
	
	public Pelanggan(){}
	public Pelanggan (String namaPelanggan,String alamatPelanggan,String emailPelanggan,String noHpPelanggan ){
		this.namaPelanggan = namaPelanggan;
		this.alamatPelanggan = alamatPelanggan;
		this.emailPelanggan = emailPelanggan;
		this.noHpPelanggan = noHpPelanggan;
	}
	
	public String getNamaPelanggan() {
		return namaPelanggan;
	}

	public void setNamaPelanggan(String namaPelanggan) {
		this.namaPelanggan = namaPelanggan;
	}

	public String getAlamatPelanggan() {
		return alamatPelanggan;
	}

	public void setAlamatPelanggan(String alamatPelanggan) {
		this.alamatPelanggan = alamatPelanggan;
	}

	public String getEmailPelanggan() {
		return emailPelanggan;
	}

	public void setEmailPelanggan(String emailPelanggan) {
		this.emailPelanggan = emailPelanggan;
	}

	public String getNoHpPelanggan() {
		return noHpPelanggan;
	}

	public void setNoHpPelanggan(String noHpPelanggan) {
		this.noHpPelanggan = noHpPelanggan;
	}
	
	public void setIdPelanggan(long id){
		this.idPelanggan = id;
	}
	public long getIdPelanggan(){
		return idPelanggan;
	}
	
}
