package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE contactList (
 	id INT NOT NULL AUTO_INCREMENT,
	MYUSER VARCHAR(30) NOT NULL,
	PHONE VARCHAR(30) NOT NULL,
	ADDRESS VARCHAR(30) NOT NULL,
	EMAIL VARCHAR(30) NOT NULL,
	NOTES VARCHAR(30) NOT NULL,
	PRIMARY KEY (ID));
 */
@Entity
@Table(name = "contactList")			//CREATE TABLE contactList(
public class Contact {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;		//id INT NOT NULL AUTO_INCREMENT,

   @Column(name = "name")
   private String name;		//name VARCHAR(50) NOT NULL,
   
   @Column(name = "phone")
   private String phone;
   
   @Column(name = "address")
   private String address;
   
   @Column(name = "email")
   private String email;
   
   @Column(name = "notes")
   private String notes;
   
   public Contact() {
   }

   public Contact(Integer id, String name, String phone, String address, String email, String notes)
   {
      this.id = id;
      this.name = name;
      this.phone = phone;
      this.address = address;
      this.email = email;
      this.notes = notes;
   }

   public Contact(String name, String phone, String address, String email, String notes) {
	   this.name = name;
	   this.phone = phone;
	   this.address = address;
	   this.email = email;
	   this.notes = notes;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhone() {
	   return phone;
   }

   public void setPhone(String phone) {
	   this.phone = phone;
   }
   
   public String getAddress() {
	   return address;
   }

   public void setAddress(String address) {
	   this.address = address;
   }
   
   public String getEmail() {
	   return email;
   }

   public void setEmail(String email) {
	   this.email = email;
   }
   
   public String getNotes() {
	   return notes;
   }

   public void setNotes(String notes) {
	   this.notes = notes;
   }
   
   
   @Override
   public String toString() {
      return "Contact: " + this.id + ", " + this.name + ", " + this.phone + ", " + 
    		   this.address + ", " + this.email + ", " + this.notes;
   }
}

