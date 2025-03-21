package com.javatechie.spring.batch.config;

import com.javatechie.spring.batch.entity.Customer;
import com.javatechie.spring.batch.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerProcessor.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerProcessor(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public Customer process(Customer customer) throws Exception {
        // Log pour suivre le traitement
        logger.info("Processing customer: {}", customer);
        //        if(customer.getCountry().equals("United States")) {
//            return customer;
//        }else{
//            return customer;
//            //return null;
//        }

        // Vérifier si le client existe déjà dans la base de données
        if (customerRepository.existsById(customer.getId())) {
            logger.info("Customer already exists with ID: {}", customer.getId());
            return null; // Ignorer ce client
        }

        // Retourner le client pour qu'il soit écrit dans la base de données
        return customer;
    }
}