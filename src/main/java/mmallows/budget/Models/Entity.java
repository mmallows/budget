package mmallows.budget.Models;

import mmallows.budget.DAO.BaseDao;

public abstract class Entity {
    protected Integer id;
    protected String name;
    protected boolean valid;

    protected abstract BaseDao<?> getDao();

    public abstract String getTableName();

    public Entity() {
        this.setName("");
        this.setValid(true);
    }

    public Entity(int id) {
        BaseDao<?> dao = this.getDao();
        if (dao != null) {
            Entity entity = dao.findById(id).stream().findFirst().orElse(null);
            if (entity != null) {
                this.id = entity.getId();
                this.name = entity.getName();
                this.valid = entity.isValid();
            } else {
                throw new IllegalArgumentException("Entity with ID " + id + " not found.");
            }
        } else {
            throw new UnsupportedOperationException("DAO not implemented for this entity.");
        }
    }

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
