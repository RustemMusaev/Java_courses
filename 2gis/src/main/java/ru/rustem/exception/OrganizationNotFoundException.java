package ru.rustem.exception;


public class OrganizationNotFoundException extends RuntimeException {
    public OrganizationNotFoundException(int id) {
        super("Organization not found id ="+ id);
    }

    public OrganizationNotFoundException(String name) {
        super("Organization not found name ="+ name);
    }
}
