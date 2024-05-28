package services;

import daos.GroupDao;
import daos.GroupDaoImpl;
import models.Group;

import java.util.List;
import java.util.UUID;

public class GroupService {
    private final GroupDao groupDao = new GroupDaoImpl();

    public void addGroup(Group group) {
        groupDao.addGroup(group);
    }

    public Group getGroupById(UUID id) {
        return groupDao.getGroup(id);
    }

    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    public void updateGroup(Group group) {
        groupDao.updateGroup(group);
    }

    public void deleteGroup(UUID id) {
        groupDao.deleteGroup(id);
    }
}
