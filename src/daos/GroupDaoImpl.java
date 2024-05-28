package daos;

import data.Db;
import exceptions.GroupNotFoundException;
import models.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupDaoImpl implements GroupDao {
    private final Db db = Db.getINSTANCE();

    @Override
    public void addGroup(Group group) {
        db.groups.put(group.getId(), group);
    }

    @Override
    public Group getGroup(UUID id) {
        Group group = db.groups.get(id);
        if (group == null) {
            throw new GroupNotFoundException();
        }
        return group;
    }

    @Override
    public List<Group> getAllGroups() {
        return new ArrayList<>(db.groups.values());
    }

    @Override
    public void updateGroup(Group newGroup) {
        Group oldGroup = db.groups.get(newGroup.getId());
        if (oldGroup == null) {
            throw new GroupNotFoundException();
        }
       db.groups.put(oldGroup.getId(), newGroup);
    }

    @Override
    public void deleteGroup(UUID id) {
        db.groups.remove(id);
    }
}
