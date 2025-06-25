package org.example;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String firstName;
    private String lastName;
    private String department;
    private String company;
    private String password;
    private int mailboxCapacity;
    private String email;
    private String alternateEmail;

    public Email(TrieST<Integer> emailStoringTrie, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = new PasswordGenerator().generator();
        askForDepartment();
        askForCompany(emailStoringTrie);
    }

    public void askForDepartment() {
        System.out.println("Enter your department:\n 0 for sales\n 1 for development\n 2 for accounting\n 3 for none ");
        Scanner input = new Scanner(System.in);
        int departmentInput = input.nextInt();
        setDepartment(departmentInput);
    }

    public void askForCompany(TrieST<Integer> emailStoringTrie) {
        System.out.println("Enter your Company");
        Scanner input = new Scanner(System.in);
        String companyInput = input.nextLine();
        setCompany(companyInput);
        generateEmail(emailStoringTrie, firstName, lastName, this.department, this.company);
    }

    private void askForEmail(TrieST<Integer> emailStoringTrie) {
        System.out.println("You will have to manually define the email");
        Scanner input = new Scanner(System.in);
        String manuallySetEmail = input.next();
        if (validateEmail(manuallySetEmail)) {
            setEmail(manuallySetEmail);
            emailStoringTrie.put(manuallySetEmail, manuallySetEmail.length());

        }
    }

    public String getDepartment() {
        System.out.println(this.department);
        return this.department;
    }

    public String getFirstName() {
        System.out.println(this.firstName);
        return this.firstName;
    }

    public String getLastName() {
        System.out.println(this.lastName);
        return this.lastName;
    }

    public String getPassword() {
        System.out.println(this.password);
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        // System.out.println(this.email);
        return this.email;
    }

    public boolean validateEmail(String alternateEmail) {
        Pattern pattern = Pattern.compile("[a-zA-Z_0-9]{5,20}\\@[a-zA-Z_0-9]{2,20}\\.[a-zA-Z]{2,3}",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alternateEmail);
        boolean matchFound = matcher.find();

        if (matchFound) {
            return true;
        } else {
            return false;
        }

    }

    public void setAlternateEmail(String alternateEmail) {
        if (validateEmail(alternateEmail)) {
            this.alternateEmail = alternateEmail;
            System.out.println("Alternative Email saved: " + alternateEmail);
        } else {
            System.out.println(
                    "Email not valid, please provide a valid email in the format [a-zA-Z_0-9]{5,20}\\@[a-zA-Z_0-9]{2,20}\\.[a-zA-Z]{2,3}");
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(int departmentNumber) {
        switch (departmentNumber) {
            case 0:
                this.department = "sales";
                break;
            case 1:
                this.department = "development";
                break;
            case 2:
                this.department = "accounting";
                break;
            default:
                this.department = "";
        }
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setMailboxCapacity(int mailboxCapacity) {
        this.mailboxCapacity = mailboxCapacity;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    private void generateEmail(TrieST<Integer> emailStoringTrie, String firstName, String lastName, String department,
            String company) {
        String firstnameSubstring = firstName.substring(0, 3);
        String lastnameSubstring = lastName.substring(0, 2);
        String newEmail = firstnameSubstring + lastnameSubstring + "@" + department + "." + company + ".com";
        if (!emailStoringTrie.contains(newEmail)) {
            email = newEmail;
            System.out.println(email + " was saved in the DB");
            emailStoringTrie.put(newEmail, email.length());
        } else {
            newEmail = firstName + lastName + "@" + department + "." + company + ".com";
            if (!emailStoringTrie.contains(newEmail)) {
                email = newEmail;
                System.out.println(email + " was saved in the DB");
                emailStoringTrie.put(newEmail, email.length());

            } else {
                askForEmail(emailStoringTrie);
            }
        }
    }

    public class PasswordGenerator {
        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
        private static final int PASSWORD_LENGTH = 15;

        public PasswordGenerator() {
        }

        public String generator() {
            SecureRandom random = new SecureRandom();
            StringBuilder newPassword = new StringBuilder(PASSWORD_LENGTH);

            for (int i = 0; i < PASSWORD_LENGTH; i++) {
                int currentCharacter = random.nextInt(CHARACTERS.length());
                newPassword.append(CHARACTERS.charAt(currentCharacter));
            }

            return newPassword.toString();
        }
    }

    /*
     * Your application should do the following:
     *  Generate an email with the following syntax:
     * firstname.lastname@department.company.com -- done
     *  Determine the department (sales, development, accounting), if none leave
     * blank -- done
     *  Generate a random String for a password -- done
     *  Have set methods to change the password, set the mailbox capacity, and
     * define an alternate
     * email address -- done
     *  Have get methods to display the name, email, and mailbox capacity -- done
     */
}
