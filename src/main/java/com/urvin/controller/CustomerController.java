package com.urvin.controller;

import com.urvin.entity.Account;
import com.urvin.entity.Customer;
import com.urvin.model.Transaction;
import com.urvin.service.AccountService;
import com.urvin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @PostMapping("/add")
    public String createCustomerAccount(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.addCustomer(customer.getCustomerName());
        Account custAccount = accountService.addAccount(updatedCustomer.getCustId());
        if(custAccount != null) {
            return "Account of "+customer.getCustomerName()+" created with CustId: "+updatedCustomer.getCustId();
        } else {
            return "Account not created";
        }
    }

    @GetMapping("/")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{custId}")
    public Account getAccountDetails(@PathVariable("custId") Long custId ) {
        return accountService.getAccountDetails(custId);
    }

    @PutMapping("/transaction")
    public String accountTransaction(@RequestBody Transaction transaction)  {
       return accountService.accountTransaction(transaction);
    }
}
