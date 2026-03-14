package com.sba301.backendpe.service;

import com.sba301.backendpe.entity.Event;
import com.sba301.backendpe.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    public Event create(Event event) {
        validate(event, null);
        return eventRepository.save(event);
    }

    public Event update(Long id, Event request) {
        Event old = getById(id);
        old.setTitle(request.getTitle());
        old.setDescription(request.getDescription());
        old.setCategory(request.getCategory());
        old.setEventDate(request.getEventDate());
        old.setSeats(request.getSeats());
        old.setOnline(request.isOnline());
        old.setLevel(request.getLevel());
        old.setBannerUrl(request.getBannerUrl());
        validate(old, id);
        return eventRepository.save(old);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    public List<String> getCategories() {
        return List.of("Tech", "Business", "Education", "Health", "Lifestyle");
    }

    public Page<Event> search(String keyword, String sort, int page, int size) {
        String safeKeyword = keyword == null ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size, resolveSort(sort));

        if (safeKeyword.isEmpty()) {
            return eventRepository.findAll(pageable);
        }

        return eventRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                        safeKeyword,
                        safeKeyword,
                        safeKeyword,
                        pageable
                );
    }

    private Sort resolveSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Direction.ASC, "id");
        }

        String[] parts = sort.split(",");
        String property = parts[0].trim();
        Sort.Direction direction = Sort.Direction.ASC;

        if (parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim())) {
            direction = Sort.Direction.DESC;
        }

        if (!List.of("id", "title", "eventDate", "seats", "category", "level").contains(property)) {
            property = "id";
        }

        return Sort.by(direction, property);
    }

    private void validate(Event event, Long editingId) {
        if (event.getTitle() == null || event.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Title is required");
        }
        if (event.getTitle().length() > 120) {
            throw new RuntimeException("Title max length is 120");
        }
        if (eventRepository.existsByTitle(event.getTitle())) {
            if (editingId == null || !eventRepository.findById(editingId)
                    .map(e -> e.getTitle().equals(event.getTitle()))
                    .orElse(false)) {
                throw new RuntimeException("Duplicate title");
            }
        }
        if (event.getDescription() == null || event.getDescription().trim().isEmpty()) {
            throw new RuntimeException("Description is required");
        }
        if (event.getDescription().length() > 1000) {
            throw new RuntimeException("Description max length is 1000");
        }
        if (event.getCategory() == null || event.getCategory().trim().isEmpty()) {
            throw new RuntimeException("Category is required");
        }
        if (event.getEventDate() == null || event.getEventDate().isBlank()) {
            throw new RuntimeException("Event date is required");
        }
        LocalDate date = LocalDate.parse(event.getEventDate());
        if (date.isBefore(LocalDate.now())) {
            throw new RuntimeException("Event date must be today or in the future");
        }
        if (event.getSeats() <= 0 || event.getSeats() > 5000) {
            throw new RuntimeException("Seats must be > 0 and <= 5000");
        }
        if (event.getLevel() == null || event.getLevel().trim().isEmpty()) {
            throw new RuntimeException("Level is required");
        }
        if (!List.of("Beginner", "Intermediate", "Advanced").contains(event.getLevel())) {
            throw new RuntimeException("Level must be Beginner, Intermediate, or Advanced");
        }
        if (event.getBannerUrl() == null || event.getBannerUrl().trim().isEmpty()) {
            throw new RuntimeException("Banner URL is required");
        }
        if (!(event.getBannerUrl().startsWith("http://") || event.getBannerUrl().startsWith("https://"))) {
            throw new RuntimeException("Banner URL must start with http:// or https://");
        }
    }
}
