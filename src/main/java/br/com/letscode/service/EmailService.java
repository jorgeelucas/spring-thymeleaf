package br.com.letscode.service;

public class EmailService {

    public EmailService(String smtp, String imap) {
    }

    public void enviar(String destino) {
        System.out.println("#######");
        System.out.println("Enviando email...");
        System.out.println("Destino=" + destino);
        System.out.println("Email enviado...");
        System.out.println("#######");
    }
}
