package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CreateDigitalServiceRequest;
import org.example.dto.DigitalServiceRest;
import org.example.exception.DuplicateException;
import org.example.exception.NotFoundException;
import org.example.model.entity.DigitalService;
import org.example.model.repository.DigitalServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DigitalServiceService {

    private final DigitalServiceRepository digitalServiceRepository;

    public void checkDigitalService(UUID subId) throws NotFoundException {
        boolean existsById = digitalServiceRepository.existsById(subId);

        if (!existsById) {
            throw new NotFoundException("No such digital service");
        }
    }

    public List<DigitalServiceRest> getByIds(List<UUID> ids) {
        List<DigitalService> allById = digitalServiceRepository.findAllById(ids);

        return allById.stream()
                .map(DigitalServiceRest::new)
                .toList();
    }

    public DigitalServiceRest createDigitalService(CreateDigitalServiceRequest request) throws DuplicateException {
        log.info("Start creating DigitalService");

        boolean existsByName = digitalServiceRepository.existsByName(request.getName());

        if (existsByName) {
            throw new DuplicateException("Such a service already exists");
        }

        DigitalService toSave = new DigitalService();
        toSave.setName(request.getName());

        DigitalService saved = digitalServiceRepository.save(toSave);

        log.info("DigitalService was created {}", saved);
        return new DigitalServiceRest(saved);
    }

    public DigitalServiceRest getById(UUID digitalServiceId) throws NotFoundException {
        DigitalService digitalService = digitalServiceRepository.findById(digitalServiceId)
                .orElseThrow(() -> new NotFoundException("Digital service not found"));

        return new DigitalServiceRest(digitalService);
    }

    public void deleteById(UUID digitalServiceId) throws NotFoundException {
        DigitalService toDelete = digitalServiceRepository.findById(digitalServiceId)
                .orElseThrow(() -> new NotFoundException("Digital service not found"));

        digitalServiceRepository.delete(toDelete);
    }
}
