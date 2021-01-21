package com.poc.demo;

import com.poc.demo.model.Employee;
import com.poc.demo.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext application = SpringApplication.run(DemoApplication.class, args);
		final MongoOperations mongoOperations = application.getBean(MongoOperations.class);
		final EmployeeRepository employeeRepository
				= application.getBean(EmployeeRepository.class);

//		if (mongoOperations.collectionExists("employee")) {
//			mongoOperations.dropCollection("employee");
//		}

		// Capped collections need to be created manually
//		mongoOperations.createCollection("employee", CollectionOptions.empty().capped().size(9999999L).maxDocuments(100L));
	}
}
