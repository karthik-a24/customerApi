package com.atos.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.atos.customer.api.CustomerApi;
import com.atos.customer.impl.CustomerApiImpl;
import com.atos.customer.model.Customer;
import com.sun.jersey.api.client.Client;

public class CustomerClientTest
{  
  Client client = Client.create();  
  @Mock
  private CustomerApi service;  
  @InjectMocks
  private CustomerApiImpl sourceToTest;
  private List<Customer> allCustomersTest = new ArrayList<Customer>();


  @Before
  public void init() throws Exception 
  {
    // Initialise the mocks
    MockitoAnnotations.initMocks(this);
    // Create the source to test i.e. CustomerApiImpl
    sourceToTest = new CustomerApiImpl();    

    // Initialise Test Customers
    Customer customer1 = new Customer(1, "Brown", "Fox");
    allCustomersTest.add(customer1);
    Customer customer2 = new Customer(2, "Jumps", "Over");
    allCustomersTest.add(customer2);
    Customer customer3 = new Customer(3, "Lazy", "Dog");
    allCustomersTest.add(customer3);
    
  }

  @Test
  public void POST_addCustomer() throws Exception 
  {    
    long customerId = 1;
    String firstName = "Brown";
    String lastName = "Fox";
    // Expected results
    Response expected = Response.status(201).entity("{\"result\":true}").build();

    // Simulate the result from the service
    when(service.addCustomer(customerId, firstName, lastName)).thenReturn(expected);

    // Get the response as actual
    final Response actual = sourceToTest.addCustomer(customerId, firstName, lastName);

    // Assert the result with the expected 
    Assert.assertEquals(expected.getEntity(), actual.getEntity());
  }

  @Test
  public void DELETE_removeCustomer() throws Exception 
  {
    long customerId = 1;
    String firstName = "Lazy";
    String lastName = "Dog";
    
    // Expected results
    Response expected = Response.ok().entity("{\"result\":true}").build();
    
    // Simulate the result from the service
    when(service.removeCustomer(customerId)).thenReturn(expected);

    // Add the customer so it will already exist in the DB
    sourceToTest.addCustomer(customerId, firstName, lastName);
    // Get the response as actual
    final Response actual = sourceToTest.removeCustomer(customerId);

    // Assert the result with the expected 
    assertEquals(expected.getEntity(), actual.getEntity());
  }

  @Test
  public void GET_listAllCustomers() throws Exception 
  {    
    // Expected results
    Response expected = Response.ok().entity(allCustomersTest.toString()).build();    
    // Simulate the result from the service
    when(service.listAllCustomers()).thenReturn(expected);
    
    // Add Customers identical to the test customers list
    sourceToTest.addCustomer(1, "Brown", "Fox");
    sourceToTest.addCustomer(2, "Jumps", "Over");
    sourceToTest.addCustomer(3, "Lazy", "Dog");
    // Get the response as actual
    final Response actual = sourceToTest.listAllCustomers();

    // Assert the result with the expected 
    assertEquals(expected.getEntity(), actual.getEntity());
  }

  @Test
  public void POST_addCustomerThatAlreadyExists() throws Exception 
  {    
    long customerId = 1;
    String firstName = "Lazy";
    String lastName = "Dog";
    // Expected results
    Response expected = Response.status(201).entity("{\"result\":false}").build();

    // Simulate the result from the service
    when(service.addCustomer(customerId, firstName, lastName)).thenReturn(expected);
    
    // Add the customer so it will already exist in the DB
    sourceToTest.addCustomer(customerId, firstName, lastName);
    // Get the response as actual
    final Response actual = sourceToTest.addCustomer(customerId, firstName, lastName);

    // Assert the result with the expected 
    Assert.assertEquals(expected.getEntity(), actual.getEntity());
  }
  
  @Test
  public void DELETE_removeCustomerThatDoesNotExist() throws Exception 
  {
    long customerId = 50;
    
    // Expected results
    Response expected = Response.ok().entity("{\"result\":false}").build();
    
    // Simulate the result from the service
    when(service.removeCustomer(customerId)).thenReturn(expected);

    // Get the response as actual
    final Response actual = sourceToTest.removeCustomer(customerId);

    // Assert the result with the expected 
    assertEquals(expected.getEntity(), actual.getEntity());
  }
  

  @Test
  public void POST_addCustomerWithIdLessThanOne() throws Exception 
  {    
    long customerId = -1;
    String firstName = "Lazy";
    String lastName = "Dog";
    // Expected results
    Response expected = Response.status(201).entity("{\"result\":false}").build();

    // Simulate the result from the service
    when(service.addCustomer(customerId, firstName, lastName)).thenReturn(expected);
    
    // Get the response as actual
    final Response actual = sourceToTest.addCustomer(customerId, firstName, lastName);

    // Assert the result with the expected 
    Assert.assertEquals(expected.getEntity(), actual.getEntity());
  }
  
  @Test
  public void DELETE_removeCustomerWithIdLessThanOne() throws Exception 
  {
    long customerId = -1;
    
    // Expected results
    Response expected = Response.ok().entity("{\"result\":false}").build();
    
    // Simulate the result from the service
    when(service.removeCustomer(customerId)).thenReturn(expected);

    // Get the response as actual
    final Response actual = sourceToTest.removeCustomer(customerId);

    // Assert the result with the expected 
    assertEquals(expected.getEntity(), actual.getEntity());
  }
}
