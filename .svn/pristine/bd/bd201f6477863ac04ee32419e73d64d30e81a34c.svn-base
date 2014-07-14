package net.felansu.sgi.util;

import org.hibernate.Session;

public class TesteHibernate {

	public static void main(String[] args) {
		Session sessao = null;
		try{
			sessao = HibernateUtil.getSessionFactory().openSession();
			System.out.println("Conectou !");
		}finally{
			sessao.close();
		}
	}

}
