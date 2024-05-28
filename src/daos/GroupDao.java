package daos;

import models.Group;

import java.util.List;
import java.util.UUID;

public interface GroupDao {
    void addGroup(Group group);
    Group getGroup(UUID id);
    List<Group> getAllGroups();
    void updateGroup(Group group);
    void deleteGroup(UUID id);
}
