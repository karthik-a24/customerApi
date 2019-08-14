package com.atos.customer.api;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
 
public interface CustomerApi
{
  public Response addCustomer(long customerId, String firstName, String lastName);
  
  public Response removeCustomer(@QueryParam("customerId") long customerId);  
  
  public Response listAllCustomers();
}
