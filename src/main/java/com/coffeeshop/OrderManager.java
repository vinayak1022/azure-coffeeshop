package com.coffeeshop;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OrderManager  {

	private static  SessionFactory sessionFactory;
	  private Session getSession()  throws Exception
	  {
	  Session s =null;
	     try{
		 		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		 		s=sessionFactory.openSession();
	 		}catch(HibernateException e){
					throw e;
		   }
		   return s;
	  }
	public  void addOrder(Order order) throws Exception
	 {
		Session s = null;
	   try{
	 		s =getSession();
	 		Transaction tx= s.beginTransaction();
	 		s.save(order);
	 		tx.commit();
	 		s.flush();
	 		s.disconnect();
	   }
	   catch(HibernateException e)
	   {
		   if(s != null)
		   {
			   s.disconnect();
		   }
		   throw e;
	   }
	}
}//end of class