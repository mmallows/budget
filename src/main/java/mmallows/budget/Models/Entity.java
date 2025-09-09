package mmallows.budget.Models;

import mmallows.budget.DAO.BaseDao;

public abstract class Entity {
    private Integer id;
    private String name;
    private boolean valid;

    public abstract BaseDao<?> getDao();

    public abstract String getTableName();

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void save() {
        BaseDao<?> dao = getDao();
        if (dao != null) {
            if (this.id == null) {
                dao.insert(this);
            } else {
                dao.update(this);
            }
        } else {
            throw new UnsupportedOperationException("DAO not implemented for this entity.");
        }
    }
}
