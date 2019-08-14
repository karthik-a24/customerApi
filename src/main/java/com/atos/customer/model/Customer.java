package com.atos.customer.model;

public class Customer
{
  long customerId     = -1; 
  String firstName    = "";
  String lastName     = "";
   
  /**
   * Custom constructor with all the attributes
   * @param id
   * @param firstName
   * @param lastName
   */
  public Customer(long customerId, String firstName, String lastName)
  {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
  }  

  public long getCustomerId()
  {
    return customerId;
  }

  public void setCustomerId(long customerId)
  {
    this.customerId = customerId;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
      sb.append("\"customerId\":" + customerId + ",");
      sb.append("\"firstName\":\"" + firstName + "\"" + ",");
      sb.append("\"lastName\":\"" + lastName + "\"");
    sb.append("}");
    return sb.toString();
  }
}
