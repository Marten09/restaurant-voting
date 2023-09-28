package ru.marten.votingforrestaurants.web.menuitem;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.model.MenuItem;
import ru.marten.votingforrestaurants.service.MenuItemService;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.assureIdConsistent;
import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNew;

public abstract class AbstractMenuItemController {
    private final Logger log = getLogger(getClass());

    @Autowired
    private MenuItemService service;

    public MenuItem create(MenuItem menuItem, int restId) {
        log.info("create {} for restaurant {}", menuItem, restId);
        checkNew(menuItem);
        return service.create(menuItem, restId);
    }

    public void update(MenuItem menuItem, int id, int restId) {
        log.info("update {} ", menuItem);
        assureIdConsistent(menuItem, id);
        service.update(menuItem, restId);
    }

    public void delete(int restId, int id) {
        log.info("delete menuItem {} for restaurant {}", id, restId);
        service.delete(restId, id);
    }

    public MenuItem get(int restId, int id) {
        log.info("get menuItem {} for restaurant {}", id, restId);
        return service.get(restId, id);
    }
}
