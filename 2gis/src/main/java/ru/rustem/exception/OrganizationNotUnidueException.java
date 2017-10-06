package ru.rustem.exception;

public class OrganizationNotUnidueException extends RuntimeException {
    public OrganizationNotUnidueException(int id) {
        super("Organization not unique id ="+ id);
    }
}
