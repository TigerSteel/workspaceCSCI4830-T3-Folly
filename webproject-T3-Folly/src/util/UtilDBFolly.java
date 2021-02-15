/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Contact;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBFolly {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Contact> listContacts() {  //Retrieve Data
      List<Contact> resultList = new ArrayList<Contact>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM Contact").list(); //From NameofDataModel
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            Contact employee = (Contact) iterator.next();
            resultList.add(employee);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<Contact> listContactsByName(String name) {
      List<Contact> resultList = new ArrayList<Contact>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM Contact").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            Contact employee = (Contact) iterator.next();
 //          if (employee.getName().startsWith(keyword)) {
            if (employee.getName().toLowerCase().contains(name.toLowerCase()))
            {
               resultList.add(employee);
            }
         }
         tx.commit();
      } 
      catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } 
      finally {
         session.close();
      }
      return resultList;
   }
   
   public static List<Contact> listContactsByPhone(String phone) {
      List<Contact> resultList = new ArrayList<Contact>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM Contact").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            Contact employee = (Contact) iterator.next();
 //          if (employee.getName().startsWith(keyword)) {
            if (employee.getPhone().contains(phone))
            {
               resultList.add(employee);
            }
         }
         tx.commit();
      } 
      catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } 
      finally 
      {
         session.close();
      }
      return resultList;
   }
   
   public static List<Contact> listContacts(String name, String phone) {
      List<Contact> resultList = new ArrayList<Contact>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM Contact").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            Contact employee = (Contact) iterator.next();
 //          if (employee.getName().startsWith(keyword)) {
            if ((employee.getName().toLowerCase().contains(name.toLowerCase()))
            		&& (employee.getPhone().contains(phone)))
            {
               resultList.add(employee);
            }
         }
         tx.commit();
      } 
      catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } 
      finally {
         session.close();
      }
      return resultList;
   }

   public static void createContact(String userName, String phone, String address, 
		   String email, String notes) {  //Data Insertion
	   
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Contact(userName, phone, address, email, notes));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
