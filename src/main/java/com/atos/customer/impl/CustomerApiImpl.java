package com.atos.customer.impl;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atos.customer.api.CustomerApi;
import com.atos.customer.model.Customer;

@Path("/customer")
public class CustomerApiImpl implements CustomerApi
{   
  /**
   * Customer API call to add a new customer
   * @param customerId
   * @param firstName
   * @param lastName
   * @return - Success/Failure
   */
  @POST 
  @Path("/addCustomer")  
  @Produces(MediaType.APPLICATION_JSON)
  public Response addCustomer(@QueryParam("customerId") long customerId, 
                              @QueryParam("firstName") String firstName, 
                              @QueryParam("lastName") String lastName)
  { 
    Customer addCustomer = new Customer(customerId, firstName, lastName);
    String result = addCustomerImpl(addCustomer);    
    // Send Response
    return Response.status(201).entity(result).build();
  }

  /**
   * Customer API to remove a customer
   * @param customerId - Unique customer ID
   * @return - Success/Failure 
   */
  @DELETE
  @Path("/removeCustomer")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response removeCustomer(@QueryParam("customerId") long customerId)
  {
    // Send response
    return Response.ok().entity(removeCustomerImpl(customerId)).build();   
  }

  /**
   * 
   * @return - List of all customers
   */
  @GET 
  @Path("/listAllCustomers") 
  @Produces(MediaType.APPLICATION_JSON) 
  public Response listAllCustomers()
  { 
    // Test response
    return Response.ok().entity(listAllCustomersImpl().toString()).build();   
  }
  
  /**
   * 
   * @param addCustomer
   * @return
   */
  public String addCustomerImpl(Customer addCustomer)
  {
    // Customer ID cannot be less than 1
    if(addCustomer.getCustomerId() < 1)
      return "{\"result\":false}"; // Return failed
    try 
    {
      // Process all customers
      for (Customer customer : allCustomers)
      {
        // Match the customer ID
        if(customer.getCustomerId() == addCustomer.getCustomerId())
        {
          return "{\"result\":false}"; // Return failed
        }
      }
      allCustomers.add(addCustomer);
      return "{\"result\":true}";  // Return success
    }
    catch(Exception e)
    {
      return "{\"result\":false}"; // Return failed
    }
  }

  /**
   * 
   * @param customerId
   * @return
   */
  public String removeCustomerImpl(long customerId)
  {
    // Customer ID cannot be less than 1
    if(customerId < 1)
      return "{\"result\":false}"; // Return failed
    try 
    {
      // Process all customers
      for (Customer customer : allCustomers)
      {
        // Match the customer ID
        if(customer.getCustomerId() == customerId)
        {
          allCustomers.remove(customer);
          return "{\"result\":true}";  // Return success
        }
      }
      // No such customer found
      return "{\"result\":false}"; // Return failed
    }
    catch(Exception e)
    {
      return "{\"result\":false}"; // Return failed
    }
  }
  
  /**
   * 
   * @return All customers
   */
  public List<Customer> listAllCustomersImpl()
  {
    return allCustomers;
  }  
 
  // Attribute to hold the customers data
  private final List<Customer> allCustomers = new ArrayList<Customer>(); 
}
