package com.group.hassocial.data.dto.enums;

public enum universityDomains {

    KHAS("stu.khas.edu.tr"),
    KOC("ku.edu.tr");


    private String uniDomain;

     universityDomains(String domain) {
        this.uniDomain = domain;
    }

    public String getUniversityDomain() {
         return uniDomain;
    }

    public static boolean checksIfDomainIsValid(String domain) {
        for (universityDomains universityDomain : universityDomains.values()) {
            if (domain.equals(universityDomain.getUniversityDomain())) return true;
        }
        return false;
    }
}
