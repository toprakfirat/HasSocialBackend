package com.group.hassocial.service;

import com.group.hassocial.repository.UniversityRepository;
import com.group.hassocial.service.interfaces.IUniversityService;
import org.springframework.stereotype.Service;

@Service
public class UniversityService implements IUniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public int extractUniversityIdFromDomain(String email) {
        String domain = email.split("@")[1];
        return universityRepository.getUniversityId(domain);
    }
}
