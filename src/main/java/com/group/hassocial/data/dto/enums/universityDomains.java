package com.group.hassocial.data.dto.enums;

import com.group.hassocial.repository.UniversityRepository;
public enum universityDomains {

    KHAS("stu.khas.edu.tr"),
    KOC("ku.edu.tr"),
    GMAIL("gmail.com");

    private final String uniDomain;

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
