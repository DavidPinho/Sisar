package com.sisar.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {
	
	
	private static final SessionFactory sessionFactory; 
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	static{
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	
	public static Session getInstance()
	  {  
		 Session session = (Session)threadLocal.get();
		 session = sessionFactory.openSession();
		 threadLocal.set(session);
		 return session;
		 
	  }

}
