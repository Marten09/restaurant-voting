package ru.marten.votingforrestaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.User;
import ru.marten.votingforrestaurants.repository.UserRepository;

import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public User get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User with " + id + " not found"));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }
}
