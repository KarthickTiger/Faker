 if (employeeRepository.count() == 0) {
            Faker faker = new Faker(); 
            List<Department> departments = departmentRepository.findAll(); 

            employeeRepository.saveAll(
                    
                    Stream.generate(() -> {
                                Employee emp = new Employee();
                                emp.setFirstName(faker.name().firstName());
                                emp.setLastName(faker.name().lastName());
                                emp.setDepartment(departments.get(faker.random().nextInt(departments.size())));
                                emp.setStatus(Employee.Status.values()[faker.random().nextInt(Employee.Status.values().length)]);

                                // Generate email
                                String email = (emp.getFirstName() + "." + emp.getLastName() + "@"
                                        + emp.getDepartment().getName().replaceAll("[\\s-]", "") + ".com").toLowerCase();
                                emp.setEmail(email);



                                return emp;
                            }).limit(50) 
                            .collect(Collectors.toList()));
        }
    }
