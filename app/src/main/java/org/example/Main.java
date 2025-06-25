package org.example;

public class Main {
    public static void main(String[] args) {
        TrieST<Integer> emailStoringTrie = new TrieST<Integer>();
        Email testEmail = new Email(emailStoringTrie, "Miguel", "Castro");
        // System.out.println(emailStoringTrie.contains(testEmail.getEmail()));
        // testEmail.setAlternateEmail("cheese@js.dk");
        Email testEmail1 = new Email(emailStoringTrie, "Miquella", "Carmona");
        Email testEmail2 = new Email(emailStoringTrie, "Miquel", "Castelo");
    }
}