package com.LatihanGradle.fajar.dataMaster.manage.pelanggan;

import java.util.List;
import java.util.ListIterator;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import com.LatihanGradle.fajar.dataMaster.model.pelanggan.Pelanggan;


public class ManagePelanggan {
	private static SessionFactory sessionFactory;
	private Session session;
	
	public static void main(String[] args){
		try{
	         sessionFactory = new Configuration().configure().buildSessionFactory();
	    }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	    }
		
		String namaPelanggan = "Fajar";
		String alamatPelanggan = "Puri 2";
		String emailPelanggan = "fajar@gmail.com";
		String noHpPelanggan = "08124124124";
		long idPelanggan;
		
		ManagePelanggan mp = new ManagePelanggan();
		/*
		/*INSERT*/
		idPelanggan=mp.addPelanggan(namaPelanggan, alamatPelanggan, emailPelanggan, noHpPelanggan);
		System.out.println("Data Pelanggan sudah masuk. dengan data " + idPelanggan + " " + namaPelanggan + " " + alamatPelanggan + " " + emailPelanggan + " " + noHpPelanggan);
		
		/*UPDATE*/
		mp.updatePelanggan(idPelanggan, "Bogor Nirwana Residence");
		Pelanggan haha = mp.searchPelanggan(idPelanggan);
		System.out.println("Data Pelanggan sudah terupdate. dengan data " + haha.getIdPelanggan() + " " + haha.getNamaPelanggan() + " " + haha.getAlamatPelanggan() + " " + haha.getEmailPelanggan() + " " + haha.getNoHpPelanggan());
		
		/*DELETE*/
		mp.deletePelanggan(haha.getIdPelanggan());
		System.out.println("Data Pelanggan dengan id " + haha.getIdPelanggan() + " telah terhapus");

		/*READ*/
		List daftarPelanggan = mp.listPelanggan();
		ListIterator<Pelanggan> iterator = daftarPelanggan.listIterator();
		System.out.println("ID          NAMA          ALAMAT          EMAIL          NO.HP");
		while(iterator.hasNext()){
			Pelanggan cetakPelanggan = (Pelanggan) iterator.next();
			System.out.print(cetakPelanggan.getIdPelanggan()+"          ");
			System.out.print(cetakPelanggan.getNamaPelanggan()+"          ");
			System.out.print(cetakPelanggan.getAlamatPelanggan()+"          ");
			System.out.print(cetakPelanggan.getEmailPelanggan()+"          ");
			System.out.print(cetakPelanggan.getNoHpPelanggan()+"          ");
			System.out.println();
		}
		
	}
	
	/*ADDING PELANGGAN*/
	public long addPelanggan(String namaPelanggan,String alamatPelanggan,
			String emailPelanggan,String noHpPelanggan){
		session = sessionFactory.openSession();
		long id = 0;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Pelanggan pelangganInput = new Pelanggan(namaPelanggan,alamatPelanggan,emailPelanggan,noHpPelanggan);
			id = (Long)session.save(pelangganInput);
			tx.commit();
		}catch (HibernateException exception){
			if(tx!=null) tx.rollback();
			exception.printStackTrace();
		}finally{
			session.close();
		}
		return id;
	}
	
	/*UPDATE PELANGGAN*/
	public void updatePelanggan(long idPelanggan, String alamatBaru){
		session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Pelanggan pelanggan = (Pelanggan)session.get(Pelanggan.class, idPelanggan);
			pelanggan.setAlamatPelanggan(alamatBaru);
			session.update(pelanggan);
			tx.commit();
		}catch (HibernateException e){
			if (tx!=null) tx.rollback();
	         e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/*SEARCH PELANGGAN*/
	public Pelanggan searchPelanggan(long idPelanggan){
		Pelanggan hasilPencarian = new Pelanggan();
		session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			hasilPencarian = (Pelanggan)session.get(Pelanggan.class, idPelanggan);
			tx.commit();
		}catch (HibernateException e){
			if (tx!=null) tx.rollback();
	         e.printStackTrace();
		}finally{
			session.close();
		}
				
		return hasilPencarian;
	}
	
	/*DELETE PELANGGAN*/
	public void deletePelanggan(long idPelanggan){
		session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Pelanggan pelanggan = (Pelanggan)session.get(Pelanggan.class, idPelanggan);
			session.delete(pelanggan);
			tx.commit();
		}catch (HibernateException e){
			if (tx!=null) tx.rollback();
	         e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/*READ PELANGGAN*/
	public List<Pelanggan> listPelanggan(){
		session = sessionFactory.openSession();
		Transaction tx = null;
		List<Pelanggan> result = null;
		try{
			tx = session.beginTransaction();
			String sql = "FROM com.LatihanGradle.fajar.dataMaster.model.pelanggan.Pelanggan";
			TypedQuery<Pelanggan> query = session.createQuery(sql);
			 result = query.getResultList();;
		}catch (HibernateException e){
			if (tx!=null) tx.rollback();
	         e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}
}
