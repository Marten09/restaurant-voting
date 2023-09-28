package ru.marten.votingforrestaurants.web.menuitem;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.marten.votingforrestaurants.model.MenuItem;

import java.net.URI;

@RestController
@RequestMapping(value = AdminMenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AdminMenuItemRestController extends AbstractMenuItemController {
    static final String REST_URL = "/api/admin/restaurants";

    @PostMapping(value = "/{restId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(@Valid @RequestBody MenuItem menuItem, @PathVariable int restId) {
        MenuItem created = super.create(menuItem, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody MenuItem menuItem, @PathVariable int restId, @PathVariable int id) {
        super.update(menuItem, id, restId);
    }

    @Override
    @DeleteMapping("/{restId}/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restId, @PathVariable int id) {
        super.delete(restId, id);
    }

    @GetMapping("/{restId}/dishes/{id}")
    public MenuItem get(@PathVariable int restId, @PathVariable int id) {
        return super.get(restId, id);
    }
}
